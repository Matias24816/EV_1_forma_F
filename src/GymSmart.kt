class GymSmart(val capacidad: Int = 10) {
    val puestos = MutableList(capacidad) { i -> Puesto(i + 1, EstadoPuesto.Libre) }
    val tickets = mutableListOf<Ticket>()

    fun registrarEntrada(equipo: Equipo) {
        if (!validarCodigo(equipo.codigo)) {
            println("Error: código de equipo inválido (${equipo.codigo})")
            return
        }

        if (equipo.usuario != TipoUsuario.Visitante &&
            equipo.usuario != TipoUsuario.Socio &&
            equipo.usuario != TipoUsuario.TerceraEdad) {
            println("Error: tipo de usuario inválido (${equipo.usuario})")
            return
        }

        val puestoLibre = puestos.find { it.estado == EstadoPuesto.Libre }
        if (puestoLibre == null) {
            println("Error: no hay puestos libres")
            return
        }

        puestoLibre.estado = EstadoPuesto.EnProceso
        println("Registrando entrada del equipo ${equipo.codigo}")

        puestoLibre.equipo = equipo
        puestoLibre.estado = EstadoPuesto.EnUso
        println("Equipo ${equipo.codigo} asignado al puesto ${puestoLibre.numero}")
    }


    fun registrarSalida(codigo: String, minutosUso: Int) {
        val puesto = puestos.find { it.equipo?.codigo == codigo }
        if (puesto == null) {
            println("Error: equipo $codigo no encontrado")
            return
        }

        puesto.estado = EstadoPuesto.EnProceso
        println("Procesando salida del equipo $codigo")

        val tarifaBase = puesto.equipo!!.calcularTarifa(minutosUso)
        val montoFinal = aplicarReglasFinales(tarifaBase, puesto.equipo!!.usuario)

        val ticket = Ticket(
            numero = tickets.size + 1,
            tipo = puesto.equipo!!::class.simpleName ?: "Desconocido",
            codigo = codigo,
            minutosUso = minutosUso,
            monto = montoFinal
        )
        tickets.add(ticket)

        println("Ticket emitido: $ticket")

        puesto.equipo = null
        puesto.estado = EstadoPuesto.Libre
    }

    fun reporteCierre() {
        println(" Reporte de cierre ")
        tickets.forEach { println(it) }
        val total = tickets.sumOf { it.monto }
        val promedio = if (tickets.isNotEmpty()) total / tickets.size else 0.0
        println("Total recaudado: $total")
        println("Equipos atendidos: ${tickets.size}")
        println("Ingreso promedio: $promedio")

        val ingresosPorTipo = tickets.groupBy { it.tipo }
            .mapValues { entry -> entry.value.sumOf { it.monto } }
        val tipoMayorIngreso = ingresosPorTipo.maxByOrNull { it.value }
        if (tipoMayorIngreso != null) {
            println("Tipo de equipo con más ingresos: ${tipoMayorIngreso.key} (${tipoMayorIngreso.value})")
        }

        val libres = puestos.count { it.estado == EstadoPuesto.Libre }
        println("Puestos disponibles al cierre: $libres")

        val equipoMayorUso = tickets.maxByOrNull { it.minutosUso }
        if (equipoMayorUso != null) {
            println("Equipo con más tiempo de uso: ${equipoMayorUso.codigo} (${equipoMayorUso.minutosUso} minutos)")
        }
    }


}
