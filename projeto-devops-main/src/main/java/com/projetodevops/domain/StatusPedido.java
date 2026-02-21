package com.projetodevops.domain;


public enum StatusPedido {
    PENDENTE{
        public StatusPedido processar(){
            return PROCESSADO;
        }

        public StatusPedido cancelar(){
            return CANCELADO;
        }
    },

    PROCESSADO{
        public StatusPedido processar(){
            throw new IllegalStateException("Já processado");
        }

        public StatusPedido cancelar(){
            throw new IllegalStateException("Não pode cancelar");
        }
    },

    CANCELADO{
        public StatusPedido processar(){
            throw new IllegalStateException("Cancelado, não pode processar");
        }

        public StatusPedido cancelar(){
            throw new IllegalStateException("Já cancelado");
        }
    };

    public abstract StatusPedido processar();
    public abstract StatusPedido cancelar();
}
