# APX1-POO
----------------------------------
Avaliação de Programação Orientada a Objeto

## Questão 1

- Dadas uma matriz de caracteres e uma string, escreva um algoritmo em Java que verifique se essa string está presente na matriz supracitada.
- A string pode estar em qualquer ordem, isto é, todos os 8 vizinhos devem ser considerados.
- Além disso, esse algoritmo não permite que o mesmo caracter seja usado mais de uma vez no encontro da string, retornando **true** se a palavra for encontrada, e **false**, caso contrário. No exemplo a seguir, se a string for **MICROSOFT** e se matriz for:

![image](https://user-images.githubusercontent.com/16018047/117516419-6ecb9b00-af6f-11eb-8790-dd0e0e676093.png)

Seu algoritmo deve retornar *true*
*Dica: a matriz deve ser lida utilizando-se a classe Scanner. Essa leitura é interrompida quando a entrada recebe a palavra FIM.*

## Questão 2

Suponha que um município nos contratou para construir um sistema para calcular o IPTU de diferentes imóveis. O uso do sistema pode ser ilustrado com o código abaixo:

```java
public class AP1_2021_1_Q2 {
  public static void main(String[] args) {
    Imovel i1 = new Imovel(100, "1/1/1980", "centro");
    System.out.println("IPTU do imóvel " + i1.getCodigo() + ": R$ " + i1.getValorIPTU());
    Imovel i2 = new Apto(100, "1/1/1980", "periferia", 2, "fundos");
    Imovel i3 = new Loja(100, "1/1/1980", "centro", false);
    Imoveis propriedades = new Imoveis(1000);
    propriedades.adicionaImovel(i1);
    propriedades.adicionaImovel(i2);
    propriedades.adicionaImovel(i3);
    System.out.println("Total de IPTU a ser arrecadado: R$ " + propriedades.calculaIPTUTotal());
  }
}
```
   Na construção do primeiro imóvel (objeto i1) são informados a metragem quadrada do imóvel, sua data de construção e sua localização (centro ou periferia), nesta ordem na chamada do construtor. Em Apto, além da metragem, data de construção e localização, são informadas a quantidade de quartos e se o apartamento é de frente ou de fundos. Em Loja, por sua vez, é importante saber se está num shopping, além das informações de metragem, data de construção e localização.
  
   Declare as classes usadas no código acima de forma que o código possa rodar sem alterações. O cálculo do IPTU é dado pela seguinte fórmula:
    ValorIPTU = ValorVenal * Alíquota
    
   Para o município em questão, a Alíquota base será de 10%, ou seja, multiplicaremos o valor venal por 0.1. Para calcular o valor venal, temos as seguintes condições:
   
   ![image](https://user-images.githubusercontent.com/16018047/117517980-6b86de00-af74-11eb-8b93-73afd83ebfe6.png)
    
   Para o cálculo da idade, a linguagem Java possui um método chamado between() que retorna o intervalo de dias, meses e anos entre 2 datas ( [Documentação Java Oracle java.time.LocalDate](https://docs.oracle.com/javase/8/docs/api/java/time/Period.html#between-java.time.LocalDate-java.time.LocalDate-) ). A data atual pode ser obtida chamando o método java.time.LocalDate.now(). Observe que a idade não deveria ser armazenada como um campo em imóvel, uma vez que, com o passar do tempo, a quantidade de anos aumentará e o valor do IPTU eventualmente será modificado. Ou seja, a idade deveria ser calculada dinamicamente, sempre que necessário.
  
  Além dessas condições, quando os imóveis são apartamentos, caso possuam até 2 quartos e a posição seja fundos, a alíquota cai para 5%. Caso seja uma loja e não esteja num shopping, a alíquota será de 8%.
  
  A classe Imoveis recebe um valor no construtor, o qual limita a quantidade de imóveis a serem inseridos. Este valor é útil quando trabalhamos com vetores, os quais precisam de um tamanho inicial para serem alocados. Caso não utilizem vetores, podem ignorar o valor passado.
  
  Para o programa acima, a saída deverá ser a seguinte:
  
  ![image](https://user-images.githubusercontent.com/16018047/117518047-adb01f80-af74-11eb-964b-d7c5eabdcbe3.png)

  Um identificador para cada imóvel deve ser gerado e mantido internamente na classe Imovel. Observe que o identificador não é informado na chamada do construtor, mas é exibido na impressão acima (analise o código fonte correspondente na main()).
  
  Utilize os conceitos de OO sempre que possível e adequado. Leia atentamente o código acima para inferir todas as classes e métodos necessários.
