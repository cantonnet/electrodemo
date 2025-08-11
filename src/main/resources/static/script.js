const API_BASE = '';

async function cargarCategorias() {
  const res = await fetch(`${API_BASE}/Categorias`);
  const categorias = await res.json();
  const select = document.getElementById("categoria");
  select.innerHTML = "";
  categorias.forEach(cat => {
    const option = document.createElement("option");
    option.value = cat.id;
    option.textContent = cat.nombre;
    select.appendChild(option);
  });
}

async function cargarProductos() {
  const res = await fetch(`${API_BASE}/Productos`);
  const productos = await res.json();
  const lista = document.getElementById("listaProductos");
  lista.innerHTML = "";
  productos.forEach(p => {
    const li = document.createElement("li");
    li.textContent = `${p.nombre} - $${p.precio} (${p.categoria.nombre})`;
    lista.appendChild(li);
  });
}

async function crearProducto(e) {
  e.preventDefault();
  const nombre = document.getElementById("nombre").value;
  const precio = parseFloat(document.getElementById("precio").value);
  const categoriaId = parseInt(document.getElementById("categoria").value);

  await fetch(`${API_BASE}/Productos`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ nombre, precio, categoriaId })
  });

  document.getElementById("formProducto").reset();
  cargarProductos();
}

async function cargarCategoriasLista() {
  const res = await fetch(`${API_BASE}/Categorias`);
  const categorias = await res.json();
  const lista = document.getElementById("listaCategorias");
  lista.innerHTML = "";
  categorias.forEach(cat => {
    const li = document.createElement("li");
    li.textContent = `${cat.id} - ${cat.nombre}`;
    lista.appendChild(li);
  });
}

async function crearCategoria(e) {
  e.preventDefault();
  const nombre = document.getElementById("nombreCategoria").value;

  await fetch(`${API_BASE}/Categorias`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ nombre })
  });

  document.getElementById("formCategoria").reset();
  cargarCategoriasLista();
}
