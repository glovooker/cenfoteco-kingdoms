package com.mygdx.game.logic.bridge_dice_buttons;

import com.mygdx.game.logic.bridge_dice_buttons.botones.BtnAccion;
import com.mygdx.game.logic.bridge_dice_buttons.botones.BtnInvocacion;
import com.mygdx.game.logic.bridge_dice_buttons.dadoMovimiento.DadoMovimiento;
import com.mygdx.game.logic.bridge_dice_buttons.estadosInvocacion.Accion;
import com.mygdx.game.logic.bridge_dice_buttons.estadosInvocacion.*;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class GestorBridge {
    private BtnInvocacion btnInvocacionInfanteria = new BtnInvocacion("Infanteria");
    private BtnInvocacion btnInvocacionTanque = new BtnInvocacion("Infanteria");
    private BtnInvocacion btnInvocacionArtilleria = new BtnInvocacion("Infanteria");
    private BtnAccion btnAtaque = new BtnAccion("Ataque");
    private BtnAccion btnAtaqueEspecial = new BtnAccion("AtaqueEspecial");
    private BtnAccion btnMovimiento = new BtnAccion("Movimiento");

    private int dadosInfanteria = 0;
    private int dadosArtilleria = 0;
    private int dadosTanque = 0;
    private int cantDadosMovimiento = 0;
    private int dadosAtaque = 0;
    private int dadosAtaqueEspecial = 0;
    private ArrayList<DadoMovimiento> dadosMovimiento = new ArrayList<>();

    Scanner scanner = new Scanner(System.in);

    private static final Map<Integer, String> TIPOS_DE_INVOCACION = Map.of(
            1, "infanteria",
            2, "artilleria",
            3, "infanteria",
            4, "artilleria",
            5, "infanteria",
            6, "tanque"
    );

    private static final Map<Integer, String> TIPOS_DE_ACCION = Map.of(
            1, "move",
            2, "attack",
            3, "sattack",
            4, "move",
            5, "attack",
            6, "sattack"
    );
    public GestorBridge() { }

    public void lanzarDado() {
        Random random = new Random();
        for (int i = 0; i < 2; i++){
            int numRandom = random.nextInt(6) + 1;
            if (TIPOS_DE_INVOCACION.getOrDefault(numRandom, null).equals("infanteria")) {
                dadosInfanteria++;
            } else if (TIPOS_DE_INVOCACION.getOrDefault(numRandom, null).equals("artilleria")) {
                dadosArtilleria++;
            } else if (TIPOS_DE_INVOCACION.getOrDefault(numRandom, null).equals("tanque")) {
                dadosTanque++;
            }

            System.out.println("Salio: " + TIPOS_DE_INVOCACION.getOrDefault(numRandom, null));
            System.out.println("Dados infanteria acumulados: " + dadosInfanteria);
            System.out.println("Dados artilleria acumulados: " + dadosArtilleria);
            System.out.println("Dados tanque acumulados: " + dadosTanque);
        }

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

        System.out.println("Salio: " + TIPOS_DE_ACCION.getOrDefault(numRandom, null));
        System.out.println("Dados movimiento acumulados: " + cantDadosMovimiento);
        System.out.println("Dados ataque acumulados: " + dadosAtaque);
        System.out.println("Dados ataque especial acumulados: " + dadosAtaqueEspecial);
    }

    public void invocarInfanteria(){
        dadosInfanteria = btnInvocacionInfanteria.getGlobalValidacion().validar(dadosInfanteria);
        btnInvocacionInfanteria.onPressed(btnInvocacionInfanteria.getGlobalValidacion().getEstado());
    }

    public void invocarArtilleria(){
        dadosArtilleria = btnInvocacionArtilleria.getGlobalValidacion().validar(dadosArtilleria);
        btnInvocacionArtilleria.onPressed(btnInvocacionArtilleria.getGlobalValidacion().getEstado());
    }

    public void invocarTanque(){
        dadosTanque = btnInvocacionTanque.getGlobalValidacion().validar(dadosTanque);
        btnInvocacionTanque.onPressed(btnInvocacionTanque.getGlobalValidacion().getEstado());
    }

    public void atacar(){
        dadosAtaque = btnAtaque.getGlobalValidacion().validar(dadosAtaque);
        btnAtaque.onPressed(btnAtaque.getGlobalValidacion().getEstado());
        System.out.println("Atacando...");
        System.out.println(dadosAtaque + " dados de ataque");
    }

    public void ejecutarAtaqueEspecial(){
        dadosAtaqueEspecial = btnAtaqueEspecial.getGlobalValidacion().validar(dadosAtaqueEspecial);
        btnAtaqueEspecial.onPressed(btnAtaqueEspecial.getGlobalValidacion().getEstado());
        System.out.println("Atacando especialmente...");
        System.out.println(dadosAtaqueEspecial + " dados de ataque especial");
    }

    public void mover(){
        cantDadosMovimiento = btnMovimiento.getGlobalValidacion().validar(cantDadosMovimiento);
        seleccionarMovimiento();
        btnMovimiento.onPressed(btnMovimiento.getGlobalValidacion().getEstado());
        System.out.println("Moviendo...");
        System.out.println(cantDadosMovimiento + " dados de movimiento");
    }

    private void seleccionarMovimiento(){
        int i = 0;
        for (DadoMovimiento dadoMovimiento : dadosMovimiento)
        {
           i++;
           System.out.println("- Dado: " + i + " con movimiento: " + dadoMovimiento.getMovimiento() + "\n");
        }
        System.out.println("Seleccione el dado que desea usar: ");
        int dadoSeleccionado = scanner.nextInt();
        System.out.println("Mover: " + dadosMovimiento.get(dadoSeleccionado).getMovimiento());
        dadosMovimiento.remove(dadoSeleccionado);
    }

    public void cambiarEstadoBotones(String tipoBtn) {
        tipoBtn = tipoBtn.toLowerCase();

        switch (tipoBtn) {
            case "infanteria" -> btnInvocacionInfanteria.setGlobalValidacion(new InvocacionInfanteriaEstado());
            case "artilleria" -> btnInvocacionArtilleria.setGlobalValidacion(new InvocacionArtilleriaEstado());
            case "tanque" -> btnInvocacionTanque.setGlobalValidacion(new InvocacionTanqueEstado());
            case "ataque" -> btnAtaque.setGlobalValidacion(new Accion());
            case "ataqueespecial" -> btnAtaqueEspecial.setGlobalValidacion(new Accion());
            case "movimiento" -> btnMovimiento.setGlobalValidacion(new Accion());
        }
    }
}