import { BrowserRouter, Routes, Route, Link } from 'react-router-dom';

import Home from './pages/Home';
import ProductDetail from './pages/ProductDetail';
import AddProduct from './pages/AddProduct';

function App() {
  return (
    <BrowserRouter>
      <nav style={{display: "flex", justifyContent: "space-around", padding: "10px", backgroundColor: "#f0f0f0"}}>
        <Link to="/">Home</Link> 
        <Link to="/add-product">Add Product</Link> 
      </nav>

      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/add-product" element={<AddProduct />} />
        <Route path="/product/:id" element={<ProductDetail />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;