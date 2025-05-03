package edu.austral.ingsis.clifford.command;

import edu.austral.ingsis.clifford.result.ExecutionResult;

/*

Actua como Comand

Define el metodo comun para todos los comandos, execute()

Los detalles de cada accion particular se encapsulan en las implementaciones de Command, en
donde se manejan varias requests a los distintos receivers como el FileSystem

Le dice a los Receiver que accion realizar

Los receivers y v alores y parametros del reciever (flags y arguments) se guardan aca

*/

public interface Command {
  ExecutionResult execute();
}
