document.addEventListener('DOMContentLoaded', function () {
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    if (id) {
        fetchProductDetails(id);
    }
});

async function fetchProductDetails(id) {
    const url = `http://54.168.221.156/api/1.0/products/details?id=${id}`;
    // const apiUrl = `http://54.168.221.156/api/1.0/products/details?id=1`;

    try {
        const response = await fetch(url);
        const data = await response.json();
        displayProductDetails(data.data);
    } catch (error) {
        console.error('Error fetching product details:', error);
    }
}

function displayProductDetails(product) {
    const leftDiv = document.getElementById('left-div');
    const rightDiv = document.getElementById('right-div');

    const img = document.createElement('img');
    img.src = product.main_image;
    leftDiv.appendChild(img);

    const titleDiv = document.createElement('div');
    titleDiv.className = 'product-title';
    titleDiv.textContent = `Title: ${product.title}`;
    rightDiv.appendChild(titleDiv);

    const productIdDiv = document.createElement('div');
    productIdDiv.textContent = `ID: ${product.id}`;
    rightDiv.appendChild(productIdDiv);

    const priceDiv = document.createElement('div');
    priceDiv.className = 'product-price';
    priceDiv.textContent = `Price: $${product.price}`;
    rightDiv.appendChild(priceDiv);

    const colorsDiv = document.createElement('div');
    colorsDiv.className = 'product-color';
    colorsDiv.textContent = 'Colors: ';
    product.colors.forEach(color => {
        const colorSquare = document.createElement('span');
        colorSquare.className = 'color-square';
        colorSquare.style.backgroundColor = color.code;
        colorsDiv.appendChild(colorSquare);
    });
    rightDiv.appendChild(colorsDiv);

    const sizesDiv = document.createElement('div');
    sizesDiv.className = 'product-size';
    sizesDiv.textContent = `Sizes: ${product.sizes.join(', ')}`;
    rightDiv.appendChild(sizesDiv);

    const stockDiv = document.createElement('div');
    stockDiv.className = 'product-stock';
    stockDiv.textContent = 'Stock: ';
    product.variants.forEach(variant => {
        const stockInfo = document.createElement('div');
        stockInfo.textContent = `Color: ${variant.color_code}, Size: ${variant.size}, Stock: ${variant.stock}`;
        stockDiv.appendChild(stockInfo);
    });
    rightDiv.appendChild(stockDiv);

    const quantityDiv = document.createElement('div');
    quantityDiv.className = 'quantity-selector';

    const minusButton = document.createElement('button');
    minusButton.textContent = '-';
    minusButton.className = 'quantity-button';
    minusButton.onclick = () => changeQuantity(-1);

    const quantityDisplay = document.createElement('span');
    quantityDisplay.textContent = '1';
    quantityDisplay.className = 'quantity-display';

    const plusButton = document.createElement('button');
    plusButton.textContent = '+';
    plusButton.className = 'quantity-button';
    plusButton.onclick = () => changeQuantity(1);

    quantityDiv.appendChild(minusButton);
    quantityDiv.appendChild(quantityDisplay);
    quantityDiv.appendChild(plusButton);

    rightDiv.appendChild(quantityDiv);

    // add purchase button (not connected to database)
    const purchaseButton = document.createElement('button');
    purchaseButton.className = 'purchase-button';
    purchaseButton.textContent = '購買';
    rightDiv.appendChild(purchaseButton);

    // whrn click purchase button, scroll down to the payment section
    purchaseButton.addEventListener('click', function () {
        const tappayContainer = document.querySelector('.container-tappay');
        if (tappayContainer) {
            tappayContainer.scrollIntoView({behavior: 'smooth'});
        }
    });

    const noteDiv = document.createElement('div');
    noteDiv.className = 'product-note';
    noteDiv.textContent = `Note: ${product.note}`;
    rightDiv.appendChild(noteDiv);

    const textureDiv = document.createElement('div');
    textureDiv.className = 'product-texture';
    textureDiv.textContent = `Texture: ${product.texture}`;
    rightDiv.appendChild(textureDiv);

    const placeDiv = document.createElement('div');
    placeDiv.className = 'product-place';
    placeDiv.textContent = `Place: ${product.place}`;
    rightDiv.appendChild(placeDiv);

    const storyDiv = document.getElementById('story');
    storyDiv.textContent = product.story;

    const pictureDiv = document.getElementById('picture');
    product.images.forEach(image => {
        const img = document.createElement('img');
        img.src = image;
        pictureDiv.appendChild(img);
    });

    function changeQuantity(amount) {
        let currentQuantity = parseInt(quantityDisplay.textContent);
        currentQuantity = Math.max(1, currentQuantity + amount); // Ensure quantity doesn't go below 1
        quantityDisplay.textContent = currentQuantity;
    }
}