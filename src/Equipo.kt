sealed class Equipo(
    val codigo: String,
    val nombre: String,
    val usuario: TipoUsuario,
    val ingreso: String
) {
    abstract fun calcularTarifa(minutosUso: Int): Double
}

class Cardio(codigo: String, nombre: String, usuario: TipoUsuario, ingreso: String) :
    Equipo(codigo, nombre, usuario, ingreso) {
    override fun calcularTarifa(minutosUso: Int): Double {
        val horas = minutosUso / 60.0
        var tarifa = 1000 * horas
        if (usuario == TipoUsuario.Socio) tarifa *= 0.8
        return tarifa
    }
}

class Fuerza(codigo: String, nombre: String, usuario: TipoUsuario, ingreso: String) :
    Equipo(codigo, nombre, usuario, ingreso) {
    override fun calcularTarifa(minutosUso: Int): Double {
        if (minutosUso < 15) return 0.0
        val horas = minutosUso / 60.0
        return 1500 * horas
    }
}

class Funcional(
    codigo: String,
    nombre: String,
    usuario: TipoUsuario,
    ingreso: String,
    val conEntrenador: Boolean
) : Equipo(codigo, nombre, usuario, ingreso) {
    override fun calcularTarifa(minutosUso: Int): Double {
        val horas = minutosUso / 60.0
        var tarifa = 2500 * horas
        if (conEntrenador) tarifa *= 1.3
        return tarifa
    }
}
