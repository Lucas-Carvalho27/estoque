import React, { useState, useEffect } from "react";
import axios from "axios";
import { Modal, Button, Form } from "react-bootstrap";
import "./styles.css"; // Arquivo CSS para customização

export default function Home() {
  const [produtos, setProdutos] = useState([]);
  const [show, setShow] = useState(false);
  const [selectedProduto, setSelectedProduto] = useState(null);
  const [produtoNome, setProdutoNome] = useState("");
  const [produtoInfo, setProdutoInfo] = useState("");
  const [categorias, setCategorias] = useState([]);

  useEffect(() => {
    loadProdutos();
  }, []);

  const loadProdutos = async () => {
    const result = await axios.get("http://localhost:8080/produto/all");
    setProdutos(result.data);
  };

  const handleShow = (produto) => {
    setSelectedProduto(produto);
    setProdutoNome(produto.ProdutoNome);
    setProdutoInfo(produto.ProdutoInfo);
    setCategorias(produto.categorias || []);
    setShow(true);
  };

  const handleClose = () => setShow(false);

  const handleSaveChanges = async () => {
    const updatedProduto = {
      id: selectedProduto.id,
      ProdutoNome: produtoNome,
      ProdutoInfo: produtoInfo,
      categorias: categorias,
    };

    try {
      await axios.put(
        `http://localhost:8080/produto/${selectedProduto.id}`,
        updatedProduto
      );
      setShow(false);
      loadProdutos();
    } catch (error) {
      console.error(
        "Erro ao atualizar o produto:",
        error.response?.data || error.message
      );
    }
  };

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8080/produto/${selectedProduto.id}`);
      setShow(false);
      loadProdutos();
    } catch (error) {
      console.error(
        "Erro ao excluir o produto:",
        error.response?.data || error.message
      );
    }
  };

  return (
    <div className="container">
      <div className="py-4">
        <table className="table table-striped table-hover">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Nome</th>
              <th scope="col">Info</th>
              <th scope="col">Categorias</th>
              <th scope="col">Ações</th>
            </tr>
          </thead>
          <tbody>
            {produtos.map((produto, index) => (
              <tr key={index}>
                <th scope="row">{index + 1}</th>
                <td>{produto.ProdutoNome}</td>
                <td>{produto.ProdutoInfo}</td>
                <td>
                  <div className="categoria-container">
                    {produto.categorias?.slice(0, 3).map((cat, idx) => (
                      <span key={idx} className="categoria-badge">
                        {cat}
                      </span>
                    ))}
                    {produto.categorias?.length > 3 && (
                      <span className="categoria-badge">+{produto.categorias.length - 3}</span>
                    )}
                  </div>
                </td>
                <td>
                  <button
                    className="btn btn-outline-primary"
                    onClick={() => handleShow(produto)}
                  >
                    Alterar
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Alterar Produto</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <Form>
            <Form.Group controlId="formProdutoNome">
              <Form.Label>Nome do Produto</Form.Label>
              <Form.Control
                type="text"
                value={produtoNome}
                onChange={(e) => setProdutoNome(e.target.value)}
              />
            </Form.Group>
            <Form.Group controlId="formProdutoInfo" className="mt-3">
              <Form.Label>Info do Produto</Form.Label>
              <Form.Control
                type="text"
                value={produtoInfo}
                onChange={(e) => setProdutoInfo(e.target.value)}
              />
            </Form.Group>
            <Form.Group controlId="formProdutoCategorias" className="mt-3">
              <Form.Label>Categorias</Form.Label>
              <Form.Control
                type="text"
                value={categorias.join(", ")}
                onChange={(e) =>
                  setCategorias(
                    e.target.value.split(",").map((cat) => cat.trim())
                  )
                }
              />
            </Form.Group>
          </Form>
        </Modal.Body>
        <Modal.Footer>
          <Button variant="secondary" onClick={handleClose}>
            Cancelar
          </Button>
          <Button variant="primary" onClick={handleSaveChanges}>
            Salvar alterações
          </Button>
          <Button variant="danger" onClick={handleDelete}>
            Excluir
          </Button>
        </Modal.Footer>
      </Modal>
    </div>
  );
}
