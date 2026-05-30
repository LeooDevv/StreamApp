package br.com.streaming.exception;

public class MidiaNaoEncontradaException extends RuntimeException {
    public MidiaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
