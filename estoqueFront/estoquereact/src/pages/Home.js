import React, { useState, useEffect } from "react";
import axios from "axios";
import { Modal, Button, Form } from "react-bootstrap";

export default function Home() {
  const [produtos, setProdutos] = useState([]);
  const [show, setShow] = useState(false);
  const [selectedProduto, setSelectedProduto] = useState(null);
  const [produtoNome, setProdutoNome] = useState("");
  const [produtoInfo, setProdutoInfo] = useState("");

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
    setShow(true);
  };

  const handleClose = () => setShow(false);

  const handleSaveChanges = async () => {
    const updatedProduto = {
      id: selectedProduto.id,
      ProdutoNome: produtoNome,
      ProdutoInfo: produtoInfo,
    };

    try {
      await axios.put(
        `http://localhost:8080/produto/${selectedProduto.id}`,
        updatedProduto
      );
      setShow(false);
      loadProdutos(); // Recarrega a lista de produtos após salvar as alterações
    } catch (error) {
      console.error("Erro ao atualizar o produto:", error.response?.data || error.message);
    }
  };

  const handleDelete = async () => {
    try {
      await axios.delete(`http://localhost:8080/produto/${selectedProduto.id}`);
      setShow(false);
      loadProdutos(); // Recarrega a lista de produtos após a exclusão
    } catch (error) {
      console.error("Erro ao excluir o produto:", error.response?.data || error.message);
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
