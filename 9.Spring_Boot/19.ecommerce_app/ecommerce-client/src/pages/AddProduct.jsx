const AddProduct = () => {
    // create submit handler for the form
    const handleSubmit = (event) => {
        event.preventDefault();
        // get form data
        const formData = new FormData(event.target);
        const productData = Object.fromEntries(formData.entries());
        console.log(productData);
    };
    return (
        <div style={{display: "flex", flexDirection: "column", alignItems: "center"}}>
            <h1>Add Product Page</h1>
            <form onSubmit={handleSubmit} style={{display: "flex", flexDirection: "column", width: "300px"}}>
                <label htmlFor="name">Name:</label>
                <input type="text" id="name" name="name" />
                <br />
                <label htmlFor="description">Description:</label>
                <textarea id="description" name="description"></textarea>
                <br />
                <label htmlFor="price">Price:</label>
                <input type="number" id="price" name="price" />
                <br />
                <label htmlFor="category">Category:</label> 
                <input type="text" id="category" name="category" />
                <br />
                <label htmlFor="brand">Brand:</label>
                <input type="text" id="brand" name="brand" />
                <br />
                <label htmlFor="quantity">Quantity:</label>
                <input type="number" id="quantity" name="quantity" />
                <br />
                <label htmlFor="releaseDate">Release Date:</label>
                <input type="date" id="releaseDate" name="releaseDate" />
                <br />
                <label htmlFor="available">Available:</label>
                <input style={{transform: "scale(1.5)"}} type="checkbox" id="available" name="available" />
                <br />
                <button type="submit">Add Product</button>
                <label htmlFor="image">Image:</label>
                <input type="file" id="image" name="image" />
            </form>
        </div>
    );
}

export default AddProduct;
