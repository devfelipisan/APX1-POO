//importacoes necessarias.
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AP1_2021_1_Q2 {
    
    public static void main(String[] args) {
        Imovel i1 = new Imovel(100, "1/1/1980", "centro");
        System.out.println("IPTU do im�vel " + i1.getCodigo() + ": R$ "
                + i1.getValorIPTU());
        Imovel i2 = new Apto(100, "1/1/1980", "periferia", 2, "fundos");
        Imovel i3 = new Loja(100, "1/1/1980", "centro", false);
        Imoveis propriedades = new Imoveis(1000);
        propriedades.adicionaImovel(i1);
        propriedades.adicionaImovel(i2);
        propriedades.adicionaImovel(i3);
        System.out.println("Total de IPTU a ser arrecadado: R$ "
                + propriedades.calculaIPTUTotal());
    }
    
    
    //Declarada a variavel fora dos objetos e com o metodo conforme solicitado.
    public static LocalDate hoje = java.time.LocalDate.now();

    private static class Imovel {

        private int metragem, codigo = 0, venal = 0;
        private double aliquota = 0.1;
        private String data, local;
        
        public void setCodigo(int codigo) {
            this.codigo += codigo;
        }
        
        public int getMetragem() {
            return metragem;
        }

        public String getData() {
            return data;
        }

        public String getLocal() {
            return local;
        }

        public int getVenal() {
            return venal;
        }

        public double getAliquota() {
            return aliquota;
        }

        public void setVenal(int venal) {
            this.venal = venal;
        }

        public void setAliquota(double aliquota) {
            this.aliquota = aliquota;
        }

        public Imovel(int m, String d, String l) {//Construtor do objeto Imovel

            this.setCodigo(1);
            this.metragem = m;
            this.data = d;
            this.local = l;
        }

        public String getCodigo() {
            String saida = String.valueOf(codigo);
            return saida;
        }

        public String getValorIPTU() {//fornece o valor do IPTU

            String ValorIPTU = "";

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");
            String inicio = this.data;

            LocalDate dtInicio = LocalDate.parse(inicio, formatter);
            Period periodo = Period.between(dtInicio, hoje);

            int idade = periodo.getYears();//Declarada variavel para idade.

            if (this.local == "Centro") {//Alterado o valor de Venal, devido a escolha do local
                this.setVenal(1000 * this.getMetragem());
            } else {
                this.setVenal(500 * this.getMetragem());
            }

            if (idade < 10) {//Altera o valor de venal conforme a idade do im�vel
                this.setVenal((int) (this.getVenal() * 0.1));
            } else if (idade > 10 && idade < 30) {
                this.setVenal((int) (this.getVenal() * 0.8));
            } else {
                this.setVenal((int) (this.getVenal() * 0.6));
            }
            
            //calculo do valor do IPTU
            ValorIPTU = String.valueOf(this.getVenal() * this.getAliquota());

            return ValorIPTU;
        }

        @Override
        public String toString() {
            return "Imovel{" + "metragem=" + metragem + ", codigo=" + codigo + ", data=" + data + ", local=" + local + '}';
        }

    }

    private static class Apto extends Imovel {
        /*
         *Criamos a classe apto extendida da classe Imovel, conforme solicitado,
         * para aproveitar das variaveis ja existentes em Imovel.
         * Metragem, data da construcao e localizacao.
         */

        private int nQuartos;
        private String sentido;

        public Apto(int m, String d, String l, int q, String s) {//construtor do objeto apto
            super(m += 1, d = d, l = l);
            this.setCodigo(1);
            this.nQuartos = q;
            this.sentido = s;
        }

        public int getnQuartos() {
            return nQuartos;
        }

        public String getSentido() {
            return sentido;
        }

        @Override //Sobreescreve conforme o objeto parametros do objeto
        public String getValorIPTU() {
            if (this.getnQuartos() == 2 && this.getSentido() == "fundos") {
                this.setAliquota(0.05);
            }
            return super.getValorIPTU();
        }

        @Override
        public String toString() {
            return "Apto{" + " metragem=" + this.getMetragem() + ", codigo=" + this.getCodigo()
                    + ", data=" + this.getData() + ", Local=" + this.getLocal() + ", nQuartos="
                    + this.getnQuartos() + ", Sentido=" + this.getSentido() + '}';
        }
    }

    private static class Loja extends Imovel {
        
        /*
         * Criamos a classe Loja extendida da classe Imovel, conforme solicitado,
         * para aproveitar das variaveis ja existentes em Imovel.
         * Metragem, data da construcao e localicacao.
         */

        private boolean shopping;

        public Loja(int m, String d, String l, boolean s) {//Construtor do objeto Shopping
            super(m += 1, d = d, l = l);
            this.setCodigo(1);
            this.shopping = s;
        }

        public boolean isShopping() {
            return shopping;
        }

        @Override //Sobreescreve conforme o objeto parametros do objeto
        public String getValorIPTU() {
            if (this.shopping) {
                this.setAliquota(0.08);
            }
            return super.getValorIPTU();
        }

        @Override
        public String toString() {
            return "Loja{" + " metragem=" + this.getMetragem() + ", codigo=" + this.getCodigo() + ", Data=" + this.getData() + ", Local=" + this.getLocal() + ", shopping=" + shopping + '}';
        }

    }

    private static class Imoveis {
        
        //declarada uma lista de im�veis
        ArrayList<Imovel> listaImovel = new ArrayList<>();

        public Imoveis(int i) {
            //optei por criar a lista dessa forma nao necessitei de um inicializador.
        }

        private void adicionaImovel(Imovel i1) {
            listaImovel.add(i1);
        }

        private String calculaIPTUTotal() {
            //calcula o total arrecadado
            double total = 0;
            for (Imovel e : listaImovel) {
                String num = String.valueOf(e.getValorIPTU());
                total += Double.parseDouble(num);
            }
            return String.valueOf(total);
        }
    }
}
