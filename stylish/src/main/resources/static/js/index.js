document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const category = urlParams.get('category');

    if (category) {
        fetchProducts(category);
    } else {
        fetchProducts("all");
    }
});

async function fetchProducts(category) {
    const url = `http://54.168.221.156/api/1.0/products/${category}`;
    try {
        const response = await fetch(url);
        // const response = await fetch('http://54.168.221.156/api/1.0/products/all');
        const data = await response.json();
        displayProducts(data.data);
    } catch (error) {
        console.error('Error fetching products:', error);
    }
}

function displayProducts(products) {
    const container = document.getElementById('productContainer');
    // container.innerHTML = ''; // Clear the container before inserting new elements

    products.forEach(product => {
        const productLink = document.createElement('a');
        productLink.href = `product.html?id=${product.id}`;
        productLink.style.textDecoration = 'none';

        const productDiv = document.createElement('div');
        productDiv.className = 'product';

        const imgDiv = document.createElement('div');
        imgDiv.className = 'product-image';
        const img = document.createElement('img');
        img.src = product.main_image;
        img.alt = 'Product Image';
        imgDiv.appendChild(img);

        const codeDiv = document.createElement('div');
        codeDiv.className = 'product-code';

        const colorCodes = product.colors.map(color => color.code);
        colorCodes.forEach(code => {
            const colorSquare = document.createElement('div');
            colorSquare.className = 'color-square';
            colorSquare.style.backgroundColor = `${code}`;
            codeDiv.appendChild(colorSquare);
        });

        const codeLabel = document.createElement('span');
        codeDiv.appendChild(codeLabel);

        const titleDiv = document.createElement('div');
        titleDiv.className = 'product-title';
        titleDiv.textContent = product.title;

        const priceDiv = document.createElement('div');
        priceDiv.className = 'product-price';
        priceDiv.textContent = `TWD: ${product.price}`;

        productDiv.appendChild(imgDiv);
        productDiv.appendChild(codeDiv);
        productDiv.appendChild(titleDiv);
        productDiv.appendChild(priceDiv);

        productLink.appendChild(productDiv);

        container.appendChild(productLink);
    });
}

document.addEventListener('DOMContentLoaded', () => {
    fetchCampaign();
});

async function fetchCampaign() {
    try {
        const response = await fetch('http://54.168.221.156/api/1.0/marketing/campaigns');
        const data = await response.json();
        // console.log("fetchCampaign: " + data.data);
        displayCampaign(data.data);
    } catch (error) {
        console.error('Error fetching campaign:', error);
    }
}

function displayCampaign(campaign) {
    const banner = document.getElementById('banner');
    const img = document.createElement('img');
    img.src = campaign[0].picture;

    banner.appendChild(img);
}