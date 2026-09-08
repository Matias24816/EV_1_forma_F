class Puesto(
    val numero: Int,
    var estado: EstadoPuesto,
    var equipo: Equipo? = null,
    var motivo: String? = null
)
