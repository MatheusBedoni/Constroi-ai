package com.app.equipe.controiai2.domain;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.app.equipe.controiai2.util.LibraryClass;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.firebase.client.Firebase;
import com.firebase.client.ValueEventListener;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Matheus on 14/01/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Profissional implements Parcelable{
    public static String TOKEN = "com.example.matheus.controiai2.domain.Profissional.TOKEN";
    public static String ID = "com.example.matheus.exemplofirebase2.domain.Profissional.ID";
    public static String TIPO = "com.example.matheus.exemplofirebase2.domain.Profissional.TIPO";
    private String nome;
    private String profissao;
    private String senha;
    private double votos;
    private int seguidores;
    private String tel;
    private int fotos;
    private int qtdFotos;
    private String descricao;
    private String experiencias;
    private String cursos;
    private String diasDisponiveis;
    private int qtd_usuarios;
    private String email;
    private String site;
    private String id;
    private String comentarioTitulo;
    private String comentario;
    private String nomeComentario;
    private String urlComentario;
    private double votoComentario;
    private double novoVotoComentario;
    private String dataComentario;
    private int favoritos;
    private int novoFavoritos;
    private int chamadas_novas;
    private int chamadas_velhas;
    private String bairro;
    private String estado;
    private String cidade;
    private int data_original;
    private int data_entrada;
    private int vip;
    private int inicioVip;
    private int terminoVip;
    private String url;
    private String primeiro;
    private String segunda;
    private String terceira;
    private String quarta;
    private String categorias;
    private int pedidoAvaliacao;
    private String dadosQuery;
    private String dadosQueryTodos;



    public int getData_original() {
        return data_original;
    }

    public void setData_original(int data_original) {
        this.data_original = data_original;
    }

    public String getDadosQueryTodos() {
        return dadosQueryTodos;
    }

    public void setDadosQueryTodos(String dadosQueryTodos) {
        this.dadosQueryTodos = dadosQueryTodos;
    }

    public String getDadosQuery() {
        return dadosQuery;
    }

    public void setDadosQuery(String dadosQuery) {
        this.dadosQuery = dadosQuery;
    }

    public String getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(String primeiro) {
        this.primeiro = primeiro;
    }

    public String getSegunda() {
        return segunda;
    }

    public String getTerceira() {
        return terceira;
    }

    public String getQuarta() {
        return quarta;
    }

    public void setSegunda(String segunda) {
        this.segunda = segunda;
    }

    public void setQuarta(String quarta) {
        this.quarta = quarta;
    }

    public void setTerceira(String terceira) {
        this.terceira = terceira;
    }

    public String getUrlComentario() {
        return urlComentario;
    }

    public void setUrlComentario(String urlComentario) {
        this.urlComentario = urlComentario;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public String getDiasDisponiveis() {
        return diasDisponiveis;
    }

    public void setDiasDisponiveis(String diasDisponiveis) {
        this.diasDisponiveis = diasDisponiveis;
    }

    public String getExperiencias() {
        return experiencias;
    }

    public void setExperiencias(String experiencias) {
        this.experiencias = experiencias;
    }

    public int getSeguidores() {
        return seguidores;
    }

    public void setSeguidores(int seguidores) {
        this.seguidores = seguidores;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    public String getUrl() {
        return url;
    }

    public int getPedidoAvaliacao() {
        return pedidoAvaliacao;
    }

    public void setPedidoAvaliacao(int pedidoAvaliacao) {
        this.pedidoAvaliacao = pedidoAvaliacao;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public double getNovoVotoComentario() {
        return novoVotoComentario;
    }

    public void setNovoVotoComentario(double novoVotoComentario) {
        this.novoVotoComentario = novoVotoComentario;
    }

    public int getNovoFavoritos() {
        return novoFavoritos;
    }

    public void setNovoFavoritos(int novoFavoritos) {
        this.novoFavoritos = novoFavoritos;
    }

    public void setInicioVip(int inicioVip) {
        this.inicioVip = inicioVip;
    }

    public int getInicioVip() {
        return inicioVip;
    }

    public void setTerminoVip(int terminoVip) {
        this.terminoVip = terminoVip;
    }

    public int getTerminoVip() {
        return terminoVip;
    }

    public void setVip(int vip) {
        this.vip = vip;
    }

    public int getVip() {
        return vip;
    }

    public void setVotoComentario(double votoComentario) {
        this.votoComentario = votoComentario;
    }

    public double getVotoComentario() {
        return votoComentario;
    }

    public void setDataComentario(String dataComentario) {
        this.dataComentario = dataComentario;
    }

    public String getDataComentario() {
        return dataComentario;
    }

    public String getBairro(){
        return bairro;
    }

    public void setBairro(String endereco) {
        this.bairro = endereco;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }



    public int getData_entrada(){
        return data_entrada;
    }

    public void setData_entrada(int data_entrada) {
        this.data_entrada = data_entrada;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getChamadas_novas() {
        return chamadas_novas;
    }

    public void setChamadas_novas(int chamadas_novas) {
        this.chamadas_novas = chamadas_novas;
    }

    public int getChamadas_velhas() {
        return chamadas_velhas;
    }

    public void setChamadas_velhas(int chamadas_velhas) {
        this.chamadas_velhas = chamadas_velhas;
    }

    public int getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(int favoritos) {
        this.favoritos = favoritos;
    }

    public void setNomeComentario(String nomeComentario) {
        this.nomeComentario = nomeComentario;
    }

    public String getNomeComentario() {
        return nomeComentario;
    }

    public void setComentarioTitulo(String comentarioTitulo){
        this.comentarioTitulo = comentarioTitulo;
    }

    public String getComentarioTitulo() {
        return comentarioTitulo;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getComentario() {
        return comentario;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Profissional(){
        descricao = " ";
        votos = 0.0;
        qtd_usuarios = 0;
        qtdFotos = 0;
        comentarioTitulo = " ";
        comentario = " ";
        nomeComentario = " ";
        favoritos = 0;
        chamadas_novas = 0;
        chamadas_velhas = 0;
        site = " ";
        data_entrada = 0;
        bairro = " ";
        cidade = " ";
        estado = " ";
        votoComentario = 0;
        dataComentario = " ";
        vip = -12;
        inicioVip = -12;
        terminoVip = 0;
        novoFavoritos = 0;
        novoVotoComentario = 0;
        url = " ";
        seguidores = 0;
        pedidoAvaliacao = 0;
        profissao = " ";
        experiencias = " ";
        diasDisponiveis = " ";
        cursos = " ";
        urlComentario = " ";
        primeiro = " ";
        segunda = " ";
        terceira = " ";
        quarta = " ";
        dadosQuery = " ";
        dadosQueryTodos = " ";
        data_original = 0;

    }
    public Profissional(String n, String p, int f){
        nome = n;
        profissao = p;
        qtd_usuarios = 0;
        votos = 5.0;
        fotos = f;
        descricao = " " ;
        tel = " ";
        qtdFotos = 0;
        comentarioTitulo = " ";
        comentario = " ";
        nomeComentario = " ";
        favoritos = 0;
        chamadas_novas = 0;
        chamadas_velhas = 0;
        site = " ";
        data_entrada = 0;
        bairro = " ";
        cidade = " ";
        estado = " ";
        votoComentario = 0;
        dataComentario = " ";
        vip = 0;
        inicioVip = 0;
        terminoVip = 0;
        novoFavoritos = 0;
        novoVotoComentario = 0;
        url = " ";
        seguidores = 0;
        pedidoAvaliacao = 0;
        experiencias = " ";
        cursos = " ";
        diasDisponiveis = " ";
        urlComentario = " ";
        primeiro = " ";
        segunda = " ";
        terceira = " ";
        quarta = " ";
        dadosQuery = " ";
        dadosQueryTodos = " ";
        data_original = 0;

    }

    public void saveTipoSP(Context context,String token){
        LibraryClass.saveSP(context,TIPO,token);
    }

    public void saveIdSP(Context context, String token){
        LibraryClass.saveSP(context,ID,token);
    }

    public void retrieveIdSP(Context context){
        this.id = LibraryClass.getSP(context,ID);
    }

    public String retrieveTipoSP(Context context){
        return LibraryClass.getSP(context,TIPO);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQtdFotos(){
        return this.qtdFotos;
    }
    public void setQtdFotos(int qtdFotos){

        this.qtdFotos = qtdFotos;
    }

    public String getDescricao(){
        return this.descricao;
    }
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }

    public int getQtd_usuarios(){
        return this.qtd_usuarios;
    }


    public void setQtd_usuarios(int qtd_usuarios){
        this.qtd_usuarios = qtd_usuarios;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getTel(){
        return this.tel;
    }

    public void setVotos(double votos)
    {
        if(this.votos > 0){
            this.votos += votos;
            this.votos /= 2;
        }
        else{
            this.votos = votos;
        }
    }
    public double getVotos() {
        return votos;
    }
    public void setFoto(int fotos){
        this.fotos = fotos;
    }
    public int getFoto(){
        return fotos;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getSenha() {
        return senha;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getProfissao() {
        return profissao;
    }
    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public void saveTokenSP(Context context, String token){
        LibraryClass.saveSP(context, TOKEN, token);
    }
    public void setPrimeiroInMap(Map<String,Object> map){
        if(getPrimeiro() != null){
            map.put("primeiro",getPrimeiro());
        }else{

        }
    }
    public void setSegundaInMap(Map<String,Object> map){
        if(getSegunda() != null){
            map.put("segunda",getSegunda());
        }else{

        }
    }
    public void setTerceiraInMap(Map<String,Object> map){
        if(getTerceira() != null){
            map.put("terceira",getTerceira());
        }else{

        }
    }
    public void setQuartaInMap(Map<String,Object> map){
        if(getQuarta() != null){
            map.put("quarta",getQuarta());
        }else{

        }
    }
    public void setSeguidoresInMap(Map<String,Object> map){
        if(getUrl() != null){
            map.put("seguidores",getSeguidores());
        }else{

        }
    }
    public void setUrlInMap(Map<String,Object> map){
        if(getUrl() != null){
            map.put("url",getUrl());
        }else{

        }
    }
    public void setVipInMap(Map<String,Object> map){
        if(getTel() != null){
            map.put("vip",getVip());
        }else{

        }
    }
    public void setNovoFavoritoInMap(Map<String,Object> map){
        if(getTel() != null){
            map.put("novoFavoritos",getNovoFavoritos());
        }else{

        }
    }
    public void setNovoVotoComentarioInMap(Map<String,Object> map){
        if(getTel() != null){
            map.put("novoVotoComentario",getNovoVotoComentario());
        }else{

        }
    }

    public void setinicioVipInMap(Map<String,Object> map){
        if(getTel() != null){
            map.put("inicioVip",getInicioVip());
        }else{

        }
    }
    public void setterminoVipInMap(Map<String,Object> map){
        if(getTel() != null){
            map.put("terminoVip",getTerminoVip());
        }else{

        }
    }
    public void setvotoComentarioInMap(Map<String,Object> map){
        if(getBairro() != null){
            map.put("votoComentario",getVotoComentario());
        }else{

        }
    }
    public void setIdInMap(Map<String,Object> map){
        if(getNome() != null){
            map.put("id",getId());
        }else{

        }
    }
    public void setUrlComentarioInMap(Map<String,Object> map){
        if(getUrlComentario() != null){
            map.put("urlComentario",getUrlComentario());
        }else{

        }
    }
    public void setdataComentarioInMap(Map<String,Object> map){
        if(getBairro() != null){
            map.put("dataComentario",getDataComentario());
        }else{

        }
    }

    public void setBairroInMap(Map<String,Object> map){
        if(getBairro() != null){
            map.put("bairro",getBairro());
        }else{

        }
    }
    public void setEstadoInMap(Map<String,Object> map){
        if(getBairro() != null){
            map.put("estado",getEstado());
        }else{

        }
    }
    public void setCidadeInMap(Map<String,Object> map){
        if(getBairro() != null){
            map.put("cidade",getCidade());
        }else{

        }
    }
    public void setNameInMap(Map<String,Object> map){
        if(getNome() != null){
            map.put("nome",getNome());
        }else{

        }
    }
    public void setSiteInMap(Map<String,Object> map){
        if(getSite() != null){
            map.put("site",getSite());
        }else{
        }
    }
    public void setDataInMap(Map<String,Object> map){
        if(getSite() != null){
            map.put("data_entrada",getData_entrada());
        }else{

        }
    }
    public void setFavoritosInMap(Map<String,Object> map){
        if(getNome() != null){
            map.put("favoritos",getFavoritos());
        }else{

        }
    }
    public void setChamadasNovasInMap(Map<String,Object> map){
        if(getNome() != null){
            map.put("chamadas_novas",getChamadas_novas());
        }else{

        }
    }
    public void setChamadasVelhasInMap(Map<String,Object> map){
        if(getNome() != null){
            map.put("chamadas_velhas",getChamadas_velhas());
        }else{

        }
    }
    public void setEmailInMap(Map<String,Object> map){
        if(getEmail() != null){
            map.put("email", getEmail());
        }
    }
    public void setDescricaoInMap(Map<String,Object>map){
        if(!getDescricao().equals(" ") )
            map.put("descricao",getDescricao());


    }
    public void setFotoInMap(Map<String,Object>map){
        if(getProfissao() != null)
            map.put("foto", getFoto());
    }
    public void setProfissaoInMap(Map<String,Object>map){
        if(getProfissao() != null)
            map.put("profissao",getProfissao());
    }
    public void setQtdFotosInMap(Map<String,Object>map){
        if(getQtdFotos() != 0)
            map.put("qtdFotos",getQtdFotos());
    }
    public void setQtd_usuariosImMap(Map<String,Object>map){
        if(getQtd_usuarios() != 0)
            map.put("qtd_usuarios",getQtd_usuarios());
    }

    public void setSenhaInMap(Map<String,Object>map){
        if(getSenha() != null)
            map.put("senha",getSenha());
    }
    public void setTelInMap(Map<String,Object>map){
        if(getTel() != null)
            map.put("tel",getTel());

    }
    public void setTituloComentarioInMap(Map<String,Object>map){
        if(getComentarioTitulo() != null)
            map.put("comentarioTitulo",getComentarioTitulo());

    }
    public void setComentarioInMap(Map<String,Object>map){
        if(getComentario() != null)
            map.put("comentario",getComentario());

    }
    public void setNomeComentarioInMap(Map<String,Object>map){
        if(getNomeComentario() != null)
            map.put("nomeComentario",getNomeComentario());

    }
    public void setPedidoAvaliacaoInMap(Map<String,Object>map){
        if(getNomeComentario() != null)
            map.put("pedidoAvaliacao",getPedidoAvaliacao());

    }
    public void setVotosInMap(Map<String,Object>map){
        if(getTel() != null)
            map.put("votos", getVotos());
    }

    public void setExperienciasInMap(Map<String,Object>map){
        if(getExperiencias() != null)
            map.put("experiencias",getExperiencias());

    }
    public void setDiasDisponiveisInMap(Map<String,Object>map){
        if(getComentario() != null)
            map.put("diasDisponiveis",getDiasDisponiveis());

    }
    public void setCursosInMap(Map<String,Object>map){
        if(getNomeComentario() != null)
            map.put("cursos",getCursos());

    }
    public void setDadosQueryInMap(Map<String,Object>map){
        if(getNomeComentario() != null)
            map.put("dadosQuery",getDadosQuery());

    }
    public void setDadosQueryTodosInMap(Map<String,Object>map){
        if(getNomeComentario() != null)
            map.put("dadosQueryTodos",getDadosQueryTodos());

    }
    public void seOriginalDadosQueryTodosInMap(Map<String,Object>map){
        if(getNomeComentario() != null)
            map.put("data_original",getData_original());

    }


    public String getTokenSP(Context context){
        String token = LibraryClass.getSP(context, TOKEN);
        return token;
    }

    public void updateDB(Firebase.CompletionListener... completionListener){
        String[] separado = getProfissao().split(",");
        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("professional").child(getEstado()).child(getCidade()).child(separado[0]).child(getId());

        Map<String,Object> map = new HashMap<>();
        setDescricaoInMap(map);
        setSenhaInMap(map);
        setFotoInMap(map);
        setEmailInMap(map);
        setQtdFotosInMap(map);
        setProfissaoInMap(map);
        setVotosInMap(map);
        setQtd_usuariosImMap(map);
        setNameInMap(map);
        setTelInMap(map);
        setComentarioInMap(map);
        setTituloComentarioInMap(map);
        setNomeComentarioInMap(map);
        setFavoritosInMap(map);
        setChamadasNovasInMap(map);
        setChamadasVelhasInMap(map);
        setSiteInMap(map);
        setDataInMap(map);
        setEstadoInMap(map);
        setCidadeInMap(map);
        setBairroInMap(map);
        setdataComentarioInMap(map);
        setvotoComentarioInMap(map);
        setVipInMap(map);
        setinicioVipInMap(map);
        setterminoVipInMap(map);
        setNovoFavoritoInMap(map);
        setIdInMap(map);
        setNovoVotoComentarioInMap(map);
        setUrlInMap(map);
        setSeguidoresInMap(map);
        setPedidoAvaliacaoInMap(map);
        setExperienciasInMap(map);
        setDiasDisponiveisInMap(map);
        setCursosInMap(map);
        setUrlComentarioInMap(map);
        setPrimeiroInMap(map);
        setSegundaInMap(map);
        setTerceiraInMap(map);
        setQuartaInMap(map);
        setDadosQueryInMap(map);
        setDadosQueryTodosInMap(map);
        seOriginalDadosQueryTodosInMap(map);

        if(map.isEmpty()){
            return;
        }
        if(completionListener != null && completionListener[0] != null){
            firebase.updateChildren(map,completionListener[0]);
        }else{
            firebase.updateChildren(map);
        }
    }

    public void updateTodosProfDB(Firebase.CompletionListener... completionListener){
        Firebase firebase = LibraryClass.getFirebase();
        firebase =firebase.child("professional").child(getEstado()).child(getCidade()).child("Todos os Profissionais").child(getId());
        Map<String,Object> map = new HashMap<>();
        setDescricaoInMap(map);
        setSenhaInMap(map);
        setFotoInMap(map);
        setEmailInMap(map);
        setQtdFotosInMap(map);
        setProfissaoInMap(map);
        setVotosInMap(map);
        setQtd_usuariosImMap(map);
        setNameInMap(map);
        setTelInMap(map);
        setIdInMap(map);
        setComentarioInMap(map);
        setTituloComentarioInMap(map);
        setNomeComentarioInMap(map);
        setFavoritosInMap(map);
        setChamadasNovasInMap(map);
        setChamadasVelhasInMap(map);
        setSiteInMap(map);
        setDataInMap(map);
        setEstadoInMap(map);
        setCidadeInMap(map);
        setBairroInMap(map);
        setdataComentarioInMap(map);
        setvotoComentarioInMap(map);
        setVipInMap(map);
        setinicioVipInMap(map);
        setterminoVipInMap(map);
        setNovoFavoritoInMap(map);
        setNovoVotoComentarioInMap(map);
        setUrlInMap(map);
        setSeguidoresInMap(map);
        setPedidoAvaliacaoInMap(map);
        setExperienciasInMap(map);
        setDiasDisponiveisInMap(map);
        setCursosInMap(map);
        setUrlComentarioInMap(map);
        setPrimeiroInMap(map);
        setSegundaInMap(map);
        setTerceiraInMap(map);
        setQuartaInMap(map);
        setDadosQueryInMap(map);
        setDadosQueryTodosInMap(map);
        seOriginalDadosQueryTodosInMap(map);

        if(map.isEmpty()){
            return;
        }
        if(completionListener != null && completionListener[0] != null){
            firebase.updateChildren(map,completionListener[0]);
        }else{
            firebase.updateChildren(map);
        }
    }

    public void updateTodosDB(Firebase.CompletionListener... completionListener){

        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("professional").child("Todos os Profissionais").child(getId());
        Map<String,Object> map = new HashMap<>();
        setDescricaoInMap(map);
        setSenhaInMap(map);
        setFotoInMap(map);
        setEmailInMap(map);
        setQtdFotosInMap(map);
        setProfissaoInMap(map);
        setVotosInMap(map);
        setQtd_usuariosImMap(map);
        setNameInMap(map);
        setIdInMap(map);
        setTelInMap(map);
        setComentarioInMap(map);
        setTituloComentarioInMap(map);
        setNomeComentarioInMap(map);
        setFavoritosInMap(map);
        setChamadasNovasInMap(map);
        setChamadasVelhasInMap(map);
        setSiteInMap(map);
        setDataInMap(map);
        setEstadoInMap(map);
        setCidadeInMap(map);
        setBairroInMap(map);
        setdataComentarioInMap(map);
        setvotoComentarioInMap(map);
        setVipInMap(map);
        setinicioVipInMap(map);
        setterminoVipInMap(map);
        setNovoFavoritoInMap(map);
        setNovoVotoComentarioInMap(map);
        setUrlInMap(map);
        setSeguidoresInMap(map);
        setPedidoAvaliacaoInMap(map);
        setExperienciasInMap(map);
        setDiasDisponiveisInMap(map);
        setCursosInMap(map);
        setUrlComentarioInMap(map);
        setPrimeiroInMap(map);
        setSegundaInMap(map);
        setTerceiraInMap(map);
        setQuartaInMap(map);
        setDadosQueryInMap(map);
        setDadosQueryTodosInMap(map);
        seOriginalDadosQueryTodosInMap(map);

        if(map.isEmpty()){
            return;
        }

        if(completionListener != null && completionListener[0] != null){
            firebase.updateChildren(map,completionListener[0]);
        }else{
            firebase.updateChildren(map);
        }
    }
    public void contextDataDB(Context context){
        retrieveIdSP(context);
        Firebase firebase = LibraryClass.getFirebase();
        firebase =firebase.child("professional").child("Todos os Profissionais").child(getId());
        firebase.addListenerForSingleValueEvent((ValueEventListener)context);
    }
    public void contextDataFacebookDB(Context context){

        Firebase firebase = LibraryClass.getFirebase();
        firebase =firebase.child("professional").child("Todos os Profissionais").child(getId());
        firebase.addListenerForSingleValueEvent((ValueEventListener)context);
    }
    public void contextDataUserDB(Context context){
        retrieveIdSP(context);
        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("users").child(getId());
        firebase.addListenerForSingleValueEvent((ValueEventListener)context);
    }



    public void addFavoritos(String id,Profissional prof){
        Firebase  firebase = LibraryClass.getFirebase();
        firebase = firebase.child("favoritos").child(getId()).child(id);
        firebase.setValue(prof);
    }
    public void dadosDB(){
        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("users").child(getId());


    }
    public void saveDB(String profissao){
        String[] separado = getProfissao().split(",");
        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("professional").child(getEstado()).child(getCidade()).child(separado[0]).child(getId());
        firebase.setValue(this);
    }

    public void saveTodosDB(){
        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("professional").child("Todos os Profissionais").child(getId());
        firebase.setValue(this);

    }
    public void saveTodosEstadoDB(){
        Firebase firebase = LibraryClass.getFirebase();
        firebase =  firebase.child("professional").child(getEstado()).child(getCidade()).child("Todos os Profissionais").child(getId());
        firebase.setValue(this);
    }

    public void removeDB( ){
        Firebase firebase = LibraryClass.getFirebase().child("professional").child("Todos os Profissionais").child(getId());
        firebase.removeValue();

    }
    public void removeEstadoDB( ){
        String[] separado = getProfissao().split(",");
        Firebase firebase = LibraryClass.getFirebase().child("professional").child(getEstado()).child(getCidade()).child(separado[0]).child(getId());
        firebase.removeValue();
    }
    public void removeEstadoTodosDB(){
        Firebase firebase = LibraryClass.getFirebase().child("professional").child(getEstado()).child(getCidade()).child("Todos os Profissionais").child(getId());
        firebase.removeValue();
    }

    public void removeChild(){
        Firebase firebase = LibraryClass.getFirebase();
        firebase = firebase.child("professional").child(getProfissao()).child(getId());
        firebase.removeValue();
    }
    //************************Parcelable***********************************************
    @Override
    public int describeContents() {
        return 0;
    }
    public Profissional(Parcel parcel) {
        this.nome = parcel.readString();
        this.profissao = parcel.readString();
        this.tel = parcel.readString();
        this.votos = parcel.readDouble();
        this.descricao = parcel.readString();
        this.qtd_usuarios = parcel.readInt();
        this.fotos = parcel.readInt();
        this.qtdFotos = parcel.readInt();
        this.senha = parcel.readString();
        this.email = parcel.readString();
        this.id = parcel.readString();
        this.comentarioTitulo = parcel.readString();
        this.comentario = parcel.readString();
        this.nomeComentario = parcel.readString();
        this.favoritos = parcel.readInt();
        this.chamadas_novas = parcel.readInt();
        this.chamadas_velhas = parcel.readInt();
        this.site = parcel.readString();
        this.data_entrada = parcel.readInt();
        this.bairro = parcel.readString();
        this.votoComentario = parcel.readDouble();
        this.dataComentario = parcel.readString();
        this.estado = parcel.readString();
        this.cidade = parcel.readString();
        this.vip = parcel.readInt();
        this.inicioVip = parcel.readInt();
        this.terminoVip = parcel.readInt();
        this.novoFavoritos = parcel.readInt();
        this.novoVotoComentario = parcel.readDouble();
        this.url = parcel.readString();
        this.seguidores = parcel.readInt();
        this.pedidoAvaliacao = parcel.readInt();
        this.experiencias = parcel.readString();
        this.diasDisponiveis = parcel.readString();
        this.cursos =parcel.readString();
        this.urlComentario = parcel.readString();
        this.primeiro = parcel.readString();
        this.segunda = parcel.readString();
        this.terceira = parcel.readString();
        this.quarta = parcel.readString();
        this.dadosQuery = parcel.readString();
        this.dadosQueryTodos = parcel.readString();
        this.data_original = parcel.readInt();

    }


    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(getNome());
        dest.writeString(getProfissao());
        dest.writeString(getTel());
        dest.writeDouble(getVotos());
        dest.writeString(getDescricao());
        dest.writeInt(getQtd_usuarios());
        dest.writeInt(getFoto());
        dest.writeInt(getQtdFotos());
        dest.writeString(getSenha());
        dest.writeString(getEmail());
        dest.writeString(getId());
        dest.writeString(getComentario());
        dest.writeString(getComentarioTitulo());
        dest.writeString(getNomeComentario());
        dest.writeInt(getFavoritos());
        dest.writeInt(getChamadas_novas());
        dest.writeInt(getChamadas_velhas());
        dest.writeString(getSite());
        dest.writeInt(getData_entrada());
        dest.writeString(getBairro());
        dest.writeDouble(getVotoComentario());
        dest.writeString(getDataComentario());
        dest.writeString(getEstado());
        dest.writeString(getCidade());
        dest.writeInt(getVip());
        dest.writeInt(getInicioVip());
        dest.writeInt(getTerminoVip());
        dest.writeInt(getNovoFavoritos());
        dest.writeDouble(getNovoVotoComentario());
        dest.writeString(getUrl());
        dest.writeInt(getSeguidores());
        dest.writeInt(getPedidoAvaliacao());
        dest.writeString(getExperiencias());
        dest.writeString(getDiasDisponiveis());
        dest.writeString(getCursos());
        dest.writeString(getUrlComentario());
        dest.writeString(getPrimeiro());
        dest.writeString(getSegunda());
        dest.writeString(getTerceira());
        dest.writeString(getQuarta());
        dest.writeString(getDadosQuery());
        dest.writeString(getDadosQueryTodos());
        dest.writeInt(getData_original());

    }
    public static final Parcelable.Creator<Profissional> CREATOR = new Parcelable.Creator<Profissional>(){

        @Override
        public Profissional createFromParcel(Parcel source) {
            return new Profissional(source);
        }

        @Override
        public Profissional[] newArray(int size) {
            // TODO Auto-generated method stub
            return new Profissional[size];
        }
    };



}
