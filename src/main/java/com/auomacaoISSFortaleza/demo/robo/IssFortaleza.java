package com.auomacaoISSFortaleza.demo.robo;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

import com.auomacaoISSFortaleza.demo.domain.exception.CNPJNotFoundException;

import org.openqa.selenium.support.ui.ExpectedConditions;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List; 

@Service
public class IssFortaleza {

    public void executarEncerramento(Long IdEmpresa, Long IdCliente) throws CNPJNotFoundException {
        System.setProperty("webdriver.chrome.driver", "C:\\caminho\\para\\chromedriver.exe");
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String mes = "mar";
        String ano = "2025";
       
        
        //****** teste com cpf que não possui paginação *****
        //T&T
        //String login = "00650644379";
        //String senha = "JTAJ.006"; 
        //String cnpj = "10.402.731/0001-17";  
        //LUAL
        String login = "44086784300";
        String senha = "Lual@2022"; 
        String cnpj = "47.636.617/0001-61"; 
        //teste coma senha errada
        //String senha = "CONST0001sd*";  
        
        
        //********* teste com cpf que possui paginacao pagina 1 ********
        //String login = "05964814387";
        //String senha = "@Mor9602"; 
        //String cnpj = "03.234.418/0001-51"; //cnpj porto
        //String cnpj = "39.796.861/0002-44"; //cnpj gvp
        
        
        // ********* teste com cpf que possui paginacao pagina 2********
        //String login = "74732765300";
        //String senha = "Tb277997@"; 
        //String cnpj = "45.963.997/0001-13"; // cnpj tbb aquiraz
        
        
        
        

        try {
        	 Logar (driver, wait, login, senha, cnpj); // concluído
        	 FazerOsAceites(driver, wait, ano, mes); //concluído
        	 Thread.sleep(2000);
        	 Encerrar (driver, wait, ano, mes); //pendente
        	 BaixarAsGuias (); //pendente
        	 BaixarPlanilhaEncerramento(); //pendente
        	 EnviaEmailCliente(); //pendente
        	 EnviaEmailEscritorioContabil(); //pendente
        	
	            
	              
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }

      
    }
    
    



  //**************************************LOGAR*****************************************
  //**************************************LOGAR*****************************************
  //**************************************LOGAR*****************************************
  //**************************************LOGAR*****************************************
    
    public void Logar (WebDriver driver, WebDriverWait wait, String login, String senha, String cnpj) throws InterruptedException, CNPJNotFoundException {
    	  	acessaOSiteInsereLoginESenha(driver, wait, login, senha);
 
            	            
            try {
            	// ****Verifica se tem erro no login ou senha****
                WebElement erroLogin = driver.findElement(By.className("login-error-msg"));
                String mensagemErro = erroLogin.getText().trim(); // Obtém o texto e remove espaços em branco

                if (!mensagemErro.isEmpty()) { // Verifica se há conteúdo na mensagem de erro
                    throw new RuntimeException("Erro: " + mensagemErro);
                }
                
            } catch (NoSuchElementException e) {
            	// Se o elemento erroLogin não existir, continua aqui
	           		            
	           try {
	        	   
	        	   WebElement modalHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("alteraInscricaoModalHeader")));
	        	   // Cria uma instância de Actions
	               Actions actions = new Actions(driver);

	               // Arrasta o elemento 300 pixels para cima (negativo no eixo Y)
	               actions.clickAndHold(modalHeader)
	                      .moveByOffset(0, -150) // X = 0 (não move horizontalmente), Y = -300 (sobe)
	                      .release()
	                      .perform();
	               
		           percorrerPaginacaoESelecionar(driver, wait, cnpj );		            
	            	
	            }  catch (NoSuchElementException | TimeoutException naoExistePaginacao) {
	            	
	            	try {
		            	// Se não houver paginacao continua aqui
		            	WebElement tbody = driver.findElement(By.id("alteraInscricaoForm:empresaDataTable:tb"));
		            	// Encontra todas as linhas <tr> dentro da tbody
		            	List<WebElement> modalListaDeEmpresasParaSelecionar = tbody.findElements(By.tagName("tr"));
	
		            	if (modalListaDeEmpresasParaSelecionar.size() > 1) {
		            	    // Faça algo aqui, como clicar em um botão dentro do modal
		            		System.out.println("existe conteudo no modal");
		            		selecionarCNPJ(driver, cnpj, 1);
			                Thread.sleep(2000);
		            		
		            	} else {
		            		System.out.println("não existe conteudo no modal");
		            	}
	            	} catch (NoSuchElementException naoExisteOModalListaEmpresaException) {
	            	    System.out.println("O elemento não foi encontrado. Seguindo com a execução...");
	            	}
	            	
	            	
	            }
            }
    }
    
    
    //-------------------------------------------------------------------------------------------------------------------
    
    public void acessaOSiteInsereLoginESenha(WebDriver driver, WebDriverWait wait, String login, String senha) throws InterruptedException {
        // Abrir o navegador na URL especificada
    	driver.get("https://iss.fortaleza.ce.gov.br/");
        Thread.sleep(2000);

        try {
            // Clicar no botão "Logar"
            WebElement loginButton = driver.findElement(By.xpath("//a[contains(@class, 'btn-pmf') and contains(@href, '/grpfor/oauth2/login')]"));
            loginButton.click();
            Thread.sleep(2000);

            // Inserir CPF e senha
            WebElement cpfLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
            cpfLogin.sendKeys(login);
            WebElement senhaLogin = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
            senhaLogin.sendKeys(senha);
            Thread.sleep(2000);

            // Clicar no botão "Entrar"
            WebElement botaoEntrar = wait.until(ExpectedConditions.elementToBeClickable(By.id("botao-entrar")));
            botaoEntrar.click();
            Thread.sleep(2000);

     
        } catch (Exception e) {
            System.err.println("Erro ao tentar acessar o site, inserir, login, senha e clicar no botao logar " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    
  //-------------------------------------------------------------------------------------------------------------------
    
    public void selecionarCNPJ(WebDriver driver, String cnpj, int paginaAtual) throws CNPJNotFoundException {
        try {
            WebElement tabela = driver.findElement(By.id("alteraInscricaoForm:empresaDataTable:tb"));
            List<WebElement> linhas = tabela.findElements(By.tagName("tr"));
            int indiceCNPJ = -1;
           

            for (int i = 0; i < linhas.size(); i++) {
                WebElement linha = linhas.get(i);
                String textoLinha = linha.getText().trim();
                String primeiros18CharCNPJ = textoLinha.length() > 18 ? textoLinha.substring(0, 18) : textoLinha;

                if (cnpj.equals(primeiros18CharCNPJ)) {
                    indiceCNPJ = i;
                    break;
                }
            }

            if (indiceCNPJ == -1) {
                throw new NoSuchElementException("CNPJ não encontrado: " + cnpj);
            }
            int indiceExatoCNPJ = indiceCNPJ;
            		 if(paginaAtual > 1) {
            			 indiceExatoCNPJ = ((paginaAtual-1) * 10) + indiceCNPJ;
            		 } 
            String idDoLinkSelecionado = "alteraInscricaoForm:empresaDataTable:" + indiceExatoCNPJ + ":linkNome";
            WebElement linkCNPJ = driver.findElement(By.id(idDoLinkSelecionado));
            linkCNPJ.click();

        } catch (NoSuchElementException e) {
            System.err.println("Erro ao buscar CNPJ: " + e.getMessage());
            throw new CNPJNotFoundException("CNPJ não encontrado: " + cnpj);
            }
    }
    
    
    
  //-------------------------------------------------------------------------------------------------------------------
    
    public void percorrerPaginacaoESelecionar(WebDriver driver, WebDriverWait wait, String cnpj) {
        int primeiraPagina = 1;
        int ultimaPagina = 1;

        try {
            // 1. Clicar no botão »» (última página) para descobrir a última página
            WebElement lastPageButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[contains(@class, 'rich-datascr-button') and text()='»»']")));
            lastPageButton.click();
            Thread.sleep(2000);

            // 2. Capturar o número da última página
            WebElement lastPageNumberElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("td.rich-datascr-act")));
            String lastPageNumber = lastPageNumberElement.getText();
            ultimaPagina = Integer.parseInt(lastPageNumber);
            Thread.sleep(2000);

            // 3. Voltar para a primeira página clicando no botão ««
            WebElement firstPageButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//td[contains(@class, 'rich-datascr-button') and text()='««']")));
            firstPageButton.click();
            Thread.sleep(2000);

            // 4. Percorrer as páginas e chamar a função selecionarCNPJ
            for (int paginaAtual = primeiraPagina; paginaAtual <= ultimaPagina; paginaAtual++) {
                try {
                    selecionarCNPJ(driver, cnpj, paginaAtual);
                    Thread.sleep(2000);
                    break;
                    
                    } catch (CNPJNotFoundException e) {
                    if (paginaAtual < ultimaPagina) {
                        // Clica no botão » para ir para a próxima página
                        WebElement nextPageButton = wait.until(ExpectedConditions.elementToBeClickable(
                            By.xpath("//td[contains(@class, 'rich-datascr-button') and text()='»']")));
                        nextPageButton.click();
                        Thread.sleep(2000);
                    }
                }
            }

        } catch (Exception e) {
            throw new NoSuchElementException("Erro ao percorrer a paginação: " + e.getMessage(), e);
        }
    }
    
    //**************************************FAZER OS ACEITES*****************************************
    //**************************************FAZER OS ACEITES*****************************************
    //**************************************FAZER OS ACEITES*****************************************
    //**************************************FAZER OS ACEITES*****************************************
    


    private void FazerOsAceites(WebDriver driver, WebDriverWait wait, String ano, String mes) throws InterruptedException {
    	WebElement linkEscrituracao = wait.until(ExpectedConditions.elementToBeClickable(
    		    By.xpath("//a[contains(text(), 'Escrituração')]")
    		));
  		linkEscrituracao.click();
  			
  			
  		WebElement linkManterEscrituracao = wait.until(ExpectedConditions.elementToBeClickable(
  			    By.id("formMenuTopo:menuEscrituracao:j_id78")
  			));
  		linkManterEscrituracao.click();
  			
  		selecionaPeriodo(wait, ano, mes);
		    
	  	// Aguarda até que o elemento esteja visível e clicável
	  	WebElement linkEscriturar = wait.until(ExpectedConditions.elementToBeClickable(By.id("manterEscrituracaoForm:dataTable:0:linkEscriturar")));

	  	// Clica no link
	  	linkEscriturar.click();
	  	Thread.sleep(8000);
	  		 
	  		 
	  	// Aguarda até que o elemento esteja visível e clicável
	  	WebElement abaServicosPendentes = wait.until(ExpectedConditions.elementToBeClickable(By.id("aba_servicos_pendentes_lbl")));

	    // Clica na aba
	  	abaServicosPendentes.click();
	  	Thread.sleep(2000);
	  		
	  		try {
	  			// Aguarda até que o link com o texto "Aceitar Todos" esteja visível e clicável
		  		WebElement aceitarTodosLink = wait.until(ExpectedConditions.elementToBeClickable(
		  		    By.xpath("//a[normalize-space(text())='Aceitar Todos']")
		  		));

		  		// Clica no link
		  		aceitarTodosLink.click();
		  		Thread.sleep(2000);
		  		// Aguarda até que o botão esteja visível e clicável
		  		WebElement botaoSim = wait.until(ExpectedConditions.elementToBeClickable(
		  		    By.id("aceite_todos_doc_tomados_modal_panel_form:btnSim")
		  		));
	
		  		// Clica no botão
		  		botaoSim.click();
		  		Thread.sleep(2000);
		  		
		  		// Aguarda até que o elemento esteja visível e clicável
		  		WebElement linkPaginaInicial = wait.until(ExpectedConditions.elementToBeClickable(
		  		    By.id("formMenuTopo:j_id25")
		  		));

		  		// Clica no link
		  		linkPaginaInicial.click();
		  		Thread.sleep(2000);
	  		} catch (TimeoutException aceitarTodosLinkException) {
	  			// Aguarda até que o elemento esteja visível e clicável
		  		WebElement linkPaginaInicial = wait.until(ExpectedConditions.elementToBeClickable(
		  		    By.id("formMenuTopo:j_id25")
		  		));

		  		// Clica no link
		  		linkPaginaInicial.click();
		  		Thread.sleep(2000);
	  		}
	  		
  		    
  	    
  			 
    	}


  //-------------------------------------------------------------------------------------------------------------------


	private void selecionaPeriodo(WebDriverWait wait, String ano, String mes) throws InterruptedException {
		// Encontra o <form> específico pelo ID
		WebElement form = wait.until(ExpectedConditions.presenceOfElementLocated(
		    By.id("manterEscrituracaoForm") // Substitua pelo ID correto do form
		));
		// Encontra a primeira tabela dentro desse form  ******ABRE AS OPCOES DE ESCOLHER OS MESES E OS ANO DA TABELA DE*******
		WebElement tabelaDe = form.findElement(By.xpath(".//table[1]"));
		// Encontra a única div dentro dessa tabela
		WebElement calendarioDivDe = tabelaDe.findElement(By.xpath(".//div"));
		// Clica na div encontrada
		calendarioDivDe.click();
		Thread.sleep(2000);
		
		// Localiza a segunda tabela dentro do form
		  WebElement tabelaDeVerdadeira = form.findElement(By.xpath(".//table[2]"));

		  // Encontra todas as <tr> dentro da tabela
		  List<WebElement> linhasDe = tabelaDeVerdadeira.findElements(By.tagName("tr"));

		  // Percorre cada <tr>  **ano**
		  outerLoop:
		  for (WebElement linha : linhasDe) {
		      // Pega todas as <div> dentro da <tr>
		      List<WebElement> divs = linha.findElements(By.tagName("div"));

		      for (WebElement div : divs) {
		          String textoDiv = div.getText().trim();
		          if (textoDiv.equals(ano)) {
		              div.click(); // Clica na <div>
		              break outerLoop; // Sai de todos os loops
		          }
		      }
		  }

		  
			// Percorre o mes
		outerLoop:
			  for (WebElement linha : linhasDe) {
			      // Pega todas as <div> dentro da <tr>
			      List<WebElement> divs = linha.findElements(By.tagName("div"));

			      for (WebElement div : divs) {
			          String textoDiv = div.getText().trim();
			          if (textoDiv.equals(mes)) {
			              div.click(); // Clica na <div>
			              break outerLoop; // Sai de todos os loops
			          }
			      }
			  }
		
		// Dentro da tabela, encontra a div com o ID específico
		WebElement botaoOKDe = tabelaDeVerdadeira.findElement(By.id("manterEscrituracaoForm:dataInicialDateEditorButtonOk"));

		// Aguarda até que o botão esteja clicável e então clica nele
		wait.until(ExpectedConditions.elementToBeClickable(botaoOKDe)).click();

		
		// Encontra a terceira tabela dentro desse form ******ABRE AS OPCOES DE ESCOLHER OS MESES E OS ANO DA TABELA ATE*******
		WebElement tabelaAte = form.findElement(By.xpath(".//table[3]"));
		// Encontra a única div dentro dessa tabela
		WebElement calendarioDivAte = tabelaAte.findElement(By.xpath(".//div"));
		// Clica na div encontrada
		calendarioDivAte.click();
		
		   WebElement tabelaAteVerdadeira = form.findElement(By.xpath(".//table[4]"));
		// Encontra todas as linhas (tr) dentro da tabela
		  List<WebElement> linhasAte = tabelaAteVerdadeira.findElements(By.tagName("tr"));

		// Percorre cada <tr>  **ano**
		  outerLoop:
		  for (WebElement linha : linhasAte) {
		      // Pega todas as <div> dentro da <tr>
		      List<WebElement> divs = linha.findElements(By.tagName("div"));

		      for (WebElement div : divs) {
		          String textoDiv = div.getText().trim();
		          if (textoDiv.equals(ano)) {
		              div.click(); // Clica na <div>
		              break outerLoop; // Sai de todos os loops
		          }
		      }
		  }

		  
			// Percorre o mes
		outerLoop:
			  for (WebElement linha : linhasAte) {
			      // Pega todas as <div> dentro da <tr>
			      List<WebElement> divs = linha.findElements(By.tagName("div"));

			      for (WebElement div : divs) {
			          String textoDiv = div.getText().trim();
			          if (textoDiv.equals(mes)) {
			              div.click(); // Clica na <div>
			              break outerLoop; // Sai de todos os loops
			          }
			      }
			  }
		
		// Dentro da tabela, encontra a div com o ID específico
		WebElement botaoOKAte = tabelaAteVerdadeira.findElement(By.id("manterEscrituracaoForm:dataFinalDateEditorButtonOk"));

		// Aguarda até que o botão esteja clicável e então clica nele
		wait.until(ExpectedConditions.elementToBeClickable(botaoOKAte)).click();
		
		
		// Aguarda até que o elemento esteja visível e clicável
		WebElement botaoConsultar = wait.until(ExpectedConditions.elementToBeClickable(By.id("manterEscrituracaoForm:btnConsultar")));

		// Clica no botão
		botaoConsultar.click();
	}
    
    
    	
    
    
    
   //**************************************ENCERRAR*****************************************
   //**************************************ENCERRAR*****************************************
   //**************************************ENCERRAR*****************************************
   //**************************************ENCERRAR*****************************************
	
	
  private void Encerrar(WebDriver driver, WebDriverWait wait, String ano, String mes) throws InterruptedException {
	 List<WebElement> menuItems = driver.findElements(By.cssSelector("ul.nav.navbar-nav > li")); 
	 menuItems.get(5).click();
	 Thread.sleep(2000);
	  
	 WebElement manterEscrituracao = driver.findElement(By.id("formMenuTopo:menuEscrituracao:j_id78"));
	 manterEscrituracao.click();
	 Thread.sleep(2000);
	  		
  	}
	
	
	
	

  //**************************************BAIXAR AS GUIAS*****************************************
  //**************************************BAIXAR AS GUIAS*****************************************
  //**************************************BAIXAR AS GUIAS*****************************************
  //**************************************BAIXAR AS GUIAS*****************************************
    private void EnviaEmailEscritorioContabil() {
  		// TODO Auto-generated method stub
  		
  	}


  private void EnviaEmailCliente() {
  		// TODO Auto-generated method stub
  		
  	}


  private void BaixarPlanilhaEncerramento() {
  		// TODO Auto-generated method stub
  		
  	}


  private void BaixarAsGuias() {
  		// TODO Auto-generated method stub
  		
  	}



   
    
    
    
    
    

    
}
