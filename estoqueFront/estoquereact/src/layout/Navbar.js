import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "bootstrap/dist/js/bootstrap.bundle.min.js";

function App() {
  const [showModal, setShowModal] = useState(false);
  const [productName, setProductName] = useState("");
  const [productInfo, setProductInfo] = useState("");

  const handleOpenModal = () => {
    setShowModal(true);
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleSaveProduct = async () => {
    const productData = {
      ProdutoNome: productName,
      ProdutoInfo: productInfo,
    };

    try {
      const response = await fetch("http://localhost:8080/produto", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(productData),
      });

      if (response.ok) {
        console.log("Produto salvo com sucesso!");
        // Opcional: limpar os campos e fechar o modal após salvar o produto
        setProductName("");
        setProductInfo("");
        setShowModal(false);
      } else {
        console.error("Erro ao salvar o produto");
      }
    } catch (error) {
      console.error("Erro ao enviar a requisição:", error);
    }
  };

  return (
    <div>
      <nav className="navbar navbar-expand-lg bg-body-tertiary">
        <div className="container-fluid">
          <a className="navbar-brand" href="#">
            Produto
          </a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          >
            <span className="navbar-toggler-icon"></span>
          </button>

          <div className="collapse navbar-collapse" id="navbarSupportedContent">
            <form className="d-flex mx-auto" role="search">
              <input
                className="form-control me-2"
                type="search"
                placeholder="Buscar produto"
                aria-label="Search"
              />
              <button className="btn btn-outline-success" type="submit">
                Buscar
              </button>
            </form>
          </div>

          <button
            className="btn btn-outline-light text-dark rounded-0"
            style={{ borderColor: "black" }}
            onClick={handleOpenModal}
          >
            Add Produto
          </button>
        </div>
      </nav>

      {/* Modal */}
      {showModal && (
        <div className="modal show" tabIndex="-1" style={{ display: "block" }}>
          <div className="modal-dialog">
            <div className="modal-content">
              <div className="modal-header">
                <h5 className="modal-title">Adicionar Produto</h5>
                <button
                  type="button"
                  className="btn-close"
                  onClick={handleCloseModal}
                ></button>
              </div>
              <div className="modal-body">
                <form>
                  <div className="mb-3">
                    <label htmlFor="productName" className="form-label">
                      Nome do Produto
                    </label>
                    <input
                      type="text"
                      className="form-control"
                      id="productName"
                      value={productName}
                      onChange={(e) => setProductName(e.target.value)}
                    />
                  </div>
                  <div className="mb-3">
                    <label htmlFor="productInfo" className="form-label">
                      Informações do Produto
                    </label>
                    <textarea
                      className="form-control"
                      id="productInfo"
                      rows="3"
                      value={productInfo}
                      onChange={(e) => setProductInfo(e.target.value)}
                    ></textarea>
                  </div>
                </form>
              </div>
              <div className="modal-footer">
                <button
                  type="button"
                  className="btn btn-secondary"
                  onClick={handleCloseModal}
                >
                  Cancelar
                </button>
                <button
                  type="button"
                  className="btn btn-primary"
                  onClick={handleSaveProduct}
                >
                  Salvar Produto
                </button>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default App;
