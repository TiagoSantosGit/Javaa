package beans;

public class BeanCursoJsp {

    private Long id;
    private String login;
    private String senha;
    private String nome;
    private String telefone;
    private String cep;
    private String cidade;
    private String fotoBase64;
    private String fotoBase64Miniatura;
    private String contenType;
    private String curriculoBase64;
    private String contenTypeCurriculo;
    private boolean ativo;
    private String sexo;

    private String tempFotoUser;
    private String tempCurriculoUser;

    public int calcula(int numero) {
        return numero * 100;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getFotoBase64() {
        return fotoBase64;
    }

    public void setFotoBase64(String fotoBase64) {
        this.fotoBase64 = fotoBase64;
    }

    public String getFotoBase64Miniatura() {
        return fotoBase64Miniatura;
    }

    public void setFotoBase64Miniatura(String fotoBase64Miniatura) {
        this.fotoBase64Miniatura = fotoBase64Miniatura;
    }

    public String getContenType() {
        return contenType;
    }

    public void setContenType(String contenType) {
        this.contenType = contenType;
    }

    public String getCurriculoBase64() {
        return curriculoBase64;
    }

    public void setCurriculoBase64(String curriculoBase64) {
        this.curriculoBase64 = curriculoBase64;
    }

    public String getContenTypeCurriculo() {
        return contenTypeCurriculo;
    }

    public void setContenTypeCurriculo(String contenTypeCurriculo) {
        this.contenTypeCurriculo = contenTypeCurriculo;
    }

    public String getTempCurriculoUser() {
        tempCurriculoUser = "data:" + contenTypeCurriculo + ";base64," + curriculoBase64;
        return tempCurriculoUser;
    }

    public String getTempFotoUser() {
        tempFotoUser = "data:" + contenType + ";base64," + fotoBase64;
        return tempFotoUser;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    @Override
    public String toString() {
        // beanCursoJSP.toString(); Descrição do objeto na memoria
        return "BeanCursoJsp [id=" + id + ", login=" + login + ", senha=" + senha + ", nome=" + nome + ", telefone="
                + telefone + "]";
    }
}
