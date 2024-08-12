package org.example.stylish.dao.daoimpl;

import org.example.stylish.dao.UserDao;
import org.example.stylish.dto.FacebookUserProfileDto;
import org.example.stylish.dto.UserDto;
import org.example.stylish.dto.UserResponseDto;
import org.example.stylish.exception.IncorrectPassword;
import org.example.stylish.exception.NonExistedAccount;
import org.example.stylish.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto createAccount(UserDto userDto) {
        // create duplicated exception in globalExceptionHandler to catch the exception
        // if email is not duplicated, then insert data into database
        // before insert, should hash the password [DONE]
        // dealing with password encoder in service layer

        // insert userDto (signUpSto) to DB
        String sql = "INSERT INTO user (name, email, password) VALUES (:name, :email, :password)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", userDto.getName());
        params.addValue("email", userDto.getEmail());
        params.addValue("password", userDto.getPassword());
        namedParameterJdbcTemplate.update(sql, params);

        // search just insert data by email to get id, provider, picture
        // DB mapper to UserResponseDto, let jwt get the required data from UserResponseDto (provider, name, email, picture)
        String sqlQuery = "SELECT * FROM user WHERE email = :email";
        MapSqlParameterSource paramForQuery = new MapSqlParameterSource();
        paramForQuery.addValue("id", userDto.getId());
        paramForQuery.addValue("provider", userDto.getProvider());
        paramForQuery.addValue("name", userDto.getName());
        paramForQuery.addValue("email", userDto.getEmail());
        paramForQuery.addValue("picture", userDto.getPicture());

        UserResponseDto userResponseDto = namedParameterJdbcTemplate.queryForObject(sqlQuery, paramForQuery, new UserRowMapper());
        return userResponseDto;
    }

    @Override
    public UserResponseDto login(UserDto userDto) {
        // add if statement to check email existence
        // count the number of rows that match the email
        String sql = "SELECT COUNT(*) FROM user WHERE email = :email";
        MapSqlParameterSource paramsForCheckExistence = new MapSqlParameterSource();
        paramsForCheckExistence.addValue("email", userDto.getEmail());
        Integer existence = namedParameterJdbcTemplate.queryForObject(sql, paramsForCheckExistence, Integer.class);
        // exception for non-existed account
        if (existence == null || existence == 0) {
            throw new NonExistedAccount("non-existed account");
        }

        // search user data by login email
        // compare hashed password between loginDto and database
        String sqlComparePassword = "SELECT password FROM user WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", userDto.getEmail());

        String password = namedParameterJdbcTemplate.queryForObject(sqlComparePassword, params, String.class);
        /* because BCrypt have salt (random 16-byte salt value is added in front of the plain text password before hash),
        can't compared hashed password directly */
        // exception for incorrect password
        if (!passwordEncoder.matches(userDto.getPassword(), password)) {
            throw new IncorrectPassword("incorrect password");
        }

        String sqlQuery = "SELECT * FROM user WHERE email = :email";
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id", userDto.getId());
        param.addValue("email", userDto.getEmail());
        param.addValue("provider", userDto.getProvider());
        param.addValue("name", userDto.getName());
        param.addValue("picture", userDto.getPicture());

        UserResponseDto userResponseDto = namedParameterJdbcTemplate.queryForObject(sqlQuery, param, new UserRowMapper());

        return userResponseDto;
    }

    //the argument should parse at service layer and map to DTO
    /* 1) check email
            a) if account non-existence, create new data from FB parse token
            b) if account exist, get the data from database
        2) a and b then pass the data (provider, name, email, picture) to generate JWT
     */
    @Override
    public UserResponseDto insertOrUpadateAccount(FacebookUserProfileDto facebookUserProfileDto) {
        UserDto userDto = new UserDto();
        userDto.setName(facebookUserProfileDto.getName());
        userDto.setEmail(facebookUserProfileDto.getEmail());
        userDto.setPicture(facebookUserProfileDto.getPicture().getData().getUrl());
//        System.out.println("userDaoImpl.insertOrUpdateAccount: " + userDto.getEmail()); // OK
//        System.out.println(userDto.getPicture()); // OK

        // check the account existence in database that email from facebook
        String sql = "SELECT COUNT(*) FROM user WHERE email = :email";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("email", userDto.getEmail());

        Integer countOfexistenceWithFacebook = namedParameterJdbcTemplate.queryForObject(sql, params, Integer.class);
        System.out.println(countOfexistenceWithFacebook.toString()); // OK
        if (countOfexistenceWithFacebook != null && countOfexistenceWithFacebook > 0) {
            // if account already existed, query data from database
            // get the existed data
            String Sql = "SELECT * FROM user WHERE email = :email";
            MapSqlParameterSource param = new MapSqlParameterSource();
            param.addValue("provider", userDto.getProvider());
            param.addValue("name", userDto.getName());
            param.addValue("email", userDto.getEmail());
            param.addValue("picture", userDto.getPicture());

            // mapped the existed date to userResponseDto
            UserResponseDto userResponseDto = namedParameterJdbcTemplate.queryForObject(Sql, param, new UserRowMapper());
//            System.out.println(userResponseDto.getProvider());
            return userResponseDto;
        } else {
            // account non-exist, create data from facebook
            // hardcode password for obey the table rule
            String insertSql = "INSERT INTO user (provider, name, email, password, picture) VALUES (:provider, :name, :email, :password, :picture)";
            MapSqlParameterSource paramForInsert = new MapSqlParameterSource();
            paramForInsert.addValue("provider", "facebook");
            paramForInsert.addValue("name", userDto.getName());
            paramForInsert.addValue("email", userDto.getEmail());
            paramForInsert.addValue("password", "passwordForTableRule");
            paramForInsert.addValue("picture", userDto.getPicture());

            namedParameterJdbcTemplate.update(insertSql, paramForInsert);
            // search inserted data by email to get JWT required data
            // DB mapper to UserResponseDto, let jwt get the required data from UserResponseDto (provider, name, email, picture)
            String Sql = "SELECT * FROM user WHERE email = :email";
            MapSqlParameterSource paramForQuery = new MapSqlParameterSource();
            paramForQuery.addValue("email", userDto.getEmail());

            UserResponseDto userResponseDto = namedParameterJdbcTemplate.queryForObject(Sql, paramForQuery, new UserRowMapper());
//            System.out.println(userResponseDto.getProvider());
            return userResponseDto;
        }
    }
}



