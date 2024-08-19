import React from "react";

export default function Navbar() {
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
          >
            Add Produto
          </button>
        </div>
      </nav>
    </div>
  );
}
