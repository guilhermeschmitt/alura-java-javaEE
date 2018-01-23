package br.com.casadocodigo.loja.service;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.casadocodigo.loja.dao.CompraDao;
import br.com.casadocodigo.loja.infra.MailSender;
import br.com.casadocodigo.loja.models.Compra;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/CarrinhoComprasTopico") })

public class EnviaEmailCompra implements MessageListener {

	@Inject
	private CompraDao compraDAO;

	@Inject
	private MailSender mailSender;

	/*
	 * Ver as configurações no standalone-full, disponível nos filesets do wildfly,
	 * colocar email e senha onde estão "SEU EMAIL AQUI" e "SUA SENHA AQUI"
	 */

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			Compra compra = compraDAO.buscaPorUuid(textMessage.getText());
			String messageBody = "Sua compra foi realizada com sucesso, com número de pedido " + compra.getUuid();
			mailSender.send("compras@casadocodigo.com.br", compra.getUsuario().getEmail(), "Nova compra na CDC",
					messageBody);
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}

}
