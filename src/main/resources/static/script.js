const API_BASE = '';

async function cargarCategorias() {
  try {
    const res = await fetch(`${API_BASE}/Categorias`);
    if (!res.ok) {
      throw new Error(`Error HTTP: ${res.status}`);
    }
    const categorias = await res.json();
    const select = document.getElementById("categoria");
    select.innerHTML = "";
    categorias.forEach(cat => {
      const option = document.createElement("option");
      option.value = cat.id;
      option.textContent = cat.nombre;
      select.appendChild(option);
    });
  } catch (error) {
    console.error('Error al cargar categorías:', error);
  }
}

async function cargarProductos() {
  try {
    const res = await fetch(`${API_BASE}/Productos`);
    if (!res.ok) {
      throw new Error(`Error HTTP: ${res.status}`);
    }
    const productos = await res.json();

    const lista = document.getElementById("listaProductos");
    lista.innerHTML = "";

    productos.forEach(p => {
      const li = document.createElement("li");
      // Verificar que categoria existe y tiene nombre antes de acceder
      const categoriaNombre = p.categoria && p.categoria.nombre ? p.categoria.nombre : '📦';
      li.textContent = `${categoriaNombre} ${p.nombre} - $${p.precio}`;
      lista.appendChild(li);
    });
  } catch (error) {
    console.error('Error al cargar productos:', error);
    const lista = document.getElementById("listaProductos");
    lista.innerHTML = "<li>Error al cargar productos</li>";
  }
}

async function crearProducto(e) {
  e.preventDefault();
  const nombre = document.getElementById("nombre").value;
  const precio = parseFloat(document.getElementById("precio").value);
  const categoriaId = parseInt(document.getElementById("categoria").value);

  try {
    const res = await fetch(`${API_BASE}/Productos`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ nombre, precio, categoriaId })
    });

    if (!res.ok) {
      throw new Error(`Error HTTP: ${res.status}`);
    }

    document.getElementById("formProducto").reset();
    cargarProductos();
  } catch (error) {
    console.error('Error al crear producto:', error);
    alert('Error al crear el producto');
  }
}

async function cargarCategoriasLista() {
  try {
    const res = await fetch(`${API_BASE}/Categorias`);
    if (!res.ok) {
      throw new Error(`Error HTTP: ${res.status}`);
    }
    const categorias = await res.json();
    const lista = document.getElementById("listaCategorias");
    lista.innerHTML = "";
    categorias.forEach(cat => {
      const li = document.createElement("li");
      li.textContent = `${cat.id} - ${cat.nombre}`;
      lista.appendChild(li);
    });
  } catch (error) {
    console.error('Error al cargar categorías:', error);
    const lista = document.getElementById("listaCategorias");
    lista.innerHTML = "<li>Error al cargar categorías</li>";
  }
}

async function crearCategoria(e) {
  e.preventDefault();
  const nombre = document.getElementById("nombreCategoria").value;

  try {
    const res = await fetch(`${API_BASE}/Categorias`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ nombre })
    });

    if (!res.ok) {
      throw new Error(`Error HTTP: ${res.status}`);
    }

    document.getElementById("formCategoria").reset();
    cargarCategoriasLista();
  } catch (error) {
    console.error('Error al crear categoría:', error);
    alert('Error al crear la categoría');
  }
}
