package com.example.luisalex.farmaciaapp;

public class Usuario {
    private int id;
    private String nick;
    private String pass;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario(String nick, String pass) {
        this.nick = nick;
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}


