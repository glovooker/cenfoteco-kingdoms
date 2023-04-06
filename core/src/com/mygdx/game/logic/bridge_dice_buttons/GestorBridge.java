package com.mygdx.game.logic.bridge_dice_buttons;

import com.mygdx.game.logic.bridge_dice_buttons.botones.BtnAccion;
import com.mygdx.game.logic.bridge_dice_buttons.botones.BtnInvocacion;
import com.mygdx.game.logic.bridge_dice_buttons.dadoMovimiento.DadoMovimiento;
import com.mygdx.game.logic.bridge_dice_buttons.estadosInvocacion.Accion;
import com.mygdx.game.logic.bridge_dice_buttons.estadosInvocacion.*;

import java.util.*;

public class GestorBridge {
    private BtnInvocacion btnInvocacionInfanteria = new BtnInvocacion("infanteria");
    private BtnInvocacion btnInvocacionTanque = new BtnInvocacion("tanque");
    private BtnInvocacion btnInvocacionArtilleria = new BtnInvocacion("artilleria");
    private BtnAccion btnAtaque = new BtnAccion("Ataque");
    private BtnAccion btnAtaqueEspecial = new BtnAccion("AtaqueEspecial");
    private BtnAccion btnMovimiento = new BtnAccion("Movimiento");

    private int dadosInfanteriaVolatil = 0;
    private int dadosInfanteriaCofre = 0;
    private int dadosInfanteriaTotal = 0;

    private int dadosArtilleriaVolatil = 0;
    private int dadosArtilleriaCofre = 0;
    private int dadosArtilleriaTotal = 0;

    private int dadosTanqueVolatil = 0;
    private int dadosTanqueCofre = 0;
    private int dadosTanqueTotal = 0;

    private ArrayList<DadoMovimiento> dadosMovimiento = new ArrayList<DadoMovimiento>();
    private int cantDadosMovimiento = 0;
    private int dadosAtaqueEspecial = 0;
    private int dadosAtaque = 0;
    Scanner scanner = new Scanner(System.in);

    private static final Map<Integer, String> TIPOS_DE_INVOCACION = new HashMap<Integer, String>() {{
        put(1, "infanteria");
        put(2, "artilleria");
        put(3, "infanteria");
        put(4, "artilleria");
        put(5, "infanteria");
        put(6, "tanque");
    }};

    private static final Map<Integer, String> TIPOS_DE_ACCION = new HashMap<Integer, String>() {{
        put(1, "move");
        put(2, "attack");
        put(3, "sattack");
        put(4, "move");
        put(5, "attack");
        put(6, "sattack");
    }};


    public GestorBridge() { }

    public String lanzarDado() {

        dadosInfanteriaVolatil = 0;
        dadosArtilleriaVolatil = 0;
        dadosTanqueVolatil = 0;

        String mensaje = "";

        Random random = new Random();

        for (int i = 0; i < 2; i++){
            int numRandom = random.nextInt(6) + 1;

            if (TIPOS_DE_INVOCACION.getOrDefault(numRandom, null).equals("infanteria")) {

                dadosInfanteriaVolatil++;
                dadosInfanteriaTotal = dadosInfanteriaVolatil + dadosInfanteriaCofre;

            } else if (TIPOS_DE_INVOCACION.getOrDefault(numRandom, null).equals("artilleria")) {

                dadosArtilleriaVolatil++;
                dadosArtilleriaTotal = dadosArtilleriaVolatil + dadosArtilleriaCofre;

            } else if (TIPOS_DE_INVOCACION.getOrDefault(numRandom, null).equals("tanque")) {

                dadosTanqueVolatil++;
                dadosTanqueTotal = dadosTanqueVolatil + dadosTanqueCofre;

            }

            mensaje += "Dado invocación " + (i+1) + ": " + TIPOS_DE_INVOCACION.getOrDefault(numRandom, null) + "\n";
        }
        mensaje += "Dados infanteria acumulados: " + dadosInfanteriaTotal + "\n";
        mensaje += "Dados artilleria acumulados: " + dadosArtilleriaTotal + "\n";
        mensaje += "Dados tanque acumulados: " + dadosTanqueTotal + "\n";

        int numRandom = random.nextInt(6) + 1;

        if (TIPOS_DE_ACCION.getOrDefault(numRandom, null).equals("move")) {

            DadoMovimiento dadoMovimiento = new DadoMovimiento();
            dadoMovimiento.setMovimiento(random.nextInt(6) + 1);

            System.out.println(dadoMovimiento.getMovimiento());

            dadosMovimiento.add(dadoMovimiento);
            cantDadosMovimiento = dadosMovimiento.size();

        } else if (TIPOS_DE_ACCION.getOrDefault(numRandom, null).equals("attack")) {
            dadosAtaque++;
        } else if (TIPOS_DE_ACCION.getOrDefault(numRandom, null).equals("sattack")) {
            dadosAtaqueEspecial++;
        }
        mensaje += "Dado acción " + TIPOS_DE_ACCION.getOrDefault(numRandom, null) + "\n";
        mensaje += "Ataques acumulados: " + dadosAtaque + "\n";
        mensaje += "Ataques especiales acumulados: " + dadosAtaqueEspecial + "\n";
        mensaje += "Movimiento acumulados: " + cantDadosMovimiento + "\n";

        return mensaje;
    }

    public void almacenarCofre(){

        dadosInfanteriaCofre += dadosInfanteriaVolatil;
        dadosInfanteriaTotal = dadosInfanteriaCofre;
        dadosInfanteriaVolatil = 0;

        dadosArtilleriaCofre += dadosArtilleriaVolatil;
        dadosArtilleriaTotal = dadosArtilleriaCofre;
        dadosArtilleriaVolatil = 0;

        dadosTanqueCofre += dadosTanqueVolatil;
        dadosTanqueTotal = dadosTanqueCofre;
        dadosTanqueVolatil = 0;

    }

    public String invocarInfanteria(){
        dadosInfanteriaTotal = btnInvocacionInfanteria.getGlobalValidacion().validar(dadosInfanteriaTotal);
        return btnInvocacionInfanteria.onPressed(btnInvocacionInfanteria.getGlobalValidacion().getEstado());
    }

    public String invocarArtilleria(){
        dadosArtilleriaTotal = btnInvocacionArtilleria.getGlobalValidacion().validar(dadosArtilleriaTotal);
        return btnInvocacionArtilleria.onPressed(btnInvocacionArtilleria.getGlobalValidacion().getEstado());
    }

    public String invocarTanque(){
        dadosTanqueTotal = btnInvocacionTanque.getGlobalValidacion().validar(dadosTanqueTotal);
        return btnInvocacionTanque.onPressed(btnInvocacionTanque.getGlobalValidacion().getEstado());
    }

    public String atacar(){
        dadosAtaque = btnAtaque.getGlobalValidacion().validar(dadosAtaque);
        return btnAtaque.onPressed(btnAtaque.getGlobalValidacion().getEstado());
    }

    public String ejecutarAtaqueEspecial(){
        dadosAtaqueEspecial = btnAtaqueEspecial.getGlobalValidacion().validar(dadosAtaqueEspecial);
        return btnAtaqueEspecial.onPressed(btnAtaqueEspecial.getGlobalValidacion().getEstado());
    }

    public String mover(){
        cantDadosMovimiento = btnMovimiento.getGlobalValidacion().validar(cantDadosMovimiento);
        if(btnMovimiento.getGlobalValidacion().getEstado())
        {
            return seleccionarMovimiento();
        } else {
            return "No tiene suficientes dados para moverse";
        }
    }

    private String seleccionarMovimiento(){
        for (DadoMovimiento dadoMovimiento : dadosMovimiento)
        {
            int i = 0;
            i++;
            System.out.println("- Dado: " + i + " con movimiento: " + dadoMovimiento.getMovimiento() + "\n");
        }
        System.out.println("Seleccione el dado que desea usar: ");
        int dadoSeleccionado = scanner.nextInt();
        String msg = "Mover: " + dadosMovimiento.get(dadoSeleccionado).getMovimiento();
        System.out.println(msg);
        dadosMovimiento.remove(dadoSeleccionado);
        return msg;
    }

    private void cambiarEstadoBotones(String tipoBtn) {
        tipoBtn = tipoBtn.toLowerCase();

        if (tipoBtn.equals("infanteria")) {
            btnInvocacionInfanteria.setGlobalValidacion(new InvocacionInfanteriaEstado());
        } else if (tipoBtn.equals("artilleria")) {
            btnInvocacionArtilleria.setGlobalValidacion(new InvocacionArtilleriaEstado());
        } else if (tipoBtn.equals("tanque")) {
            btnInvocacionTanque.setGlobalValidacion(new InvocacionTanqueEstado());
        } else if (tipoBtn.equals("ataque")) {
            btnAtaque.setGlobalValidacion(new Accion());
        } else if (tipoBtn.equals("ataqueespecial")) {
            btnAtaqueEspecial.setGlobalValidacion(new Accion());
        } else if (tipoBtn.equals("movimiento")) {
            btnMovimiento.setGlobalValidacion(new Accion());
        }
    }

    public void iniciarBotones(){
        cambiarEstadoBotones("infanteria");
        cambiarEstadoBotones("artilleria");
        cambiarEstadoBotones("tanque");
        cambiarEstadoBotones("ataque");
        cambiarEstadoBotones("ataqueEspecial");
        cambiarEstadoBotones("movimiento");
    }
}