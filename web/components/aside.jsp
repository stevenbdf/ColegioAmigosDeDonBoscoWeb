<div class="col-2">
    <div class="text-center">
        <img width="50%" class="rounded-circle my-3" src="https://revistavive.com/wp-content/uploads/2020/05/DON-BOSCO-2.png" />
    </div>
    <h5>Bienvenido</h5>
    <div class="card mb-3">
        <div class="card-body d-flex flex-column">
            <p class="m-0">${sessionScope.idRol == '3' ? 'Estudiante: ' :  'Profesor: '} ${sessionScope.usuario}</p>
        </div>
    </div>
    <h5>Idiomas</h5>
    <div class="card">
        <div class="card-body d-flex flex-column">
            <a class="stretched-link mb-3">Español</a>
            <a class="stretched-link">Ingles</a>
        </div>
    </div>
</div>