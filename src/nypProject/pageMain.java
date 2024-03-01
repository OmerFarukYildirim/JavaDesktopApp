package nypProject;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.ActionListener;import java.io.CharArrayReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;


public class pageMain {

	private JFrame frame;
	private JTextField subscriberNameTextField;
	private JTextField creditCardNumberTextField;
	private JTextField creditCardCVCTextField;
	private JTextField bankCodeTextField;
	private JTextField bankDMYTextField;
	private JTextField bankNameTextField;
	private JTextField bankAccountNumberTextField;
	private JPanel journeyPanel = new JPanel();
	private JPanel subscribersPanel = new JPanel();
	private JPanel corporatePanel = new JPanel();
	private	JComboBox billingTypeComboBox = new JComboBox();
	private JComboBox creditCardMonthComboBox = new JComboBox();
	private JComboBox creditCardYearComboBox = new JComboBox();
	private JPanel mainPagePanel = new JPanel();
	private JPanel individualPanel = new JPanel();
	private JTextPane addressTextPane = new JTextPane();

	private Distributor distributor;
	private JTable subscriberTable;
	
	DefaultTableModel subscribersTableModel=new DefaultTableModel();
	private JTextField journalNameTextField;
	private JTextField journalIssnTextField;
	private JTextField journalPriceTextField;
	private JComboBox journalFrequencyComboBox = new JComboBox();

	private JTable journeysTable;
	DefaultTableModel journeysTableModel=new DefaultTableModel();
	private JTextField searchJourneyTextField;
	private JTextField searchSubscriberTextField;
	private JTextField mainIssnTextField;
	private JTextField mainStartMonthTextField;
	private JTextField mainStartYearTextField;
	private JTextField mainPaymentPriceTextField;
	private JTable mainTable;
	DefaultTableModel mainTableModel=new DefaultTableModel();

	private JList mainSubscriberList = new JList();
    DefaultListModel<String> subscriberListModel = new DefaultListModel<>();
    private JTextField mainFilterIssnTextField;
    private JTextField mainFilterMonthTextField;
    private JTextField mainFilterYearTextField;
    private JTextField mainSearchTextField;
	private JMenuItem mainMenuMenuItem = new JMenuItem("Main Page");
	private JMenuItem journeyMenuItem = new JMenuItem("Journey");
	private JMenuItem subscribersMenuItem = new JMenuItem("Subscribers");
	
	private JButton mainSubscriptionAddButton = new JButton("Add");
	private JButton addSubscriversButton = new JButton("Add");
	private JButton journalAddButton = new JButton("Add");
	private JButton loadStateButton = new JButton("Load State");
	private JButton listNameOrIssnButton = new JButton("List");
	private JButton listSendingOrdersButton = new JButton("List Sending Orders");
	private JButton inCompletePaymentsButton = new JButton("In Complete Payments");

	private JTextField mainDiscountTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					pageMain window = new pageMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public pageMain() {
		distributor=new Distributor();
		initialize();
		actionListeners();
		subscribersTableModel=(DefaultTableModel) subscriberTable.getModel();
		journeysTableModel=(DefaultTableModel) journeysTable.getModel();
		subscriberListModel=new DefaultListModel<>();
		mainSubscriberList.setModel(subscriberListModel);
		
		mainDiscountTextField = new JTextField();
		mainDiscountTextField.setBounds(93, 312, 130, 26);
		mainPagePanel.add(mainDiscountTextField);
		mainDiscountTextField.setColumns(10);
		
		JLabel lblNewLabel_24 = new JLabel("Discount %");
		lblNewLabel_24.setBounds(6, 317, 80, 16);
		mainPagePanel.add(lblNewLabel_24);
		
		
		
		mainTableModel=(DefaultTableModel) mainTable.getModel();

		
		corporatePanel.setVisible(false);
		individualPanel.setVisible(false);
		fillSubscriberTable(null);
		fillJourneysTable(null);
		fillMainTable(null);
		mainPagePanel.setVisible(true);
		journeyPanel.setVisible(false);
		subscribersPanel.setVisible(false);
	}
	private void fillSubscriberList() {
		Vector<Subscriber> subscribers=distributor.getSubscribers();
		System.out.println("subscribers size:"+subscribers.size());
		subscriberListModel.clear();
		for(Subscriber subscriber:subscribers) {
		subscriberListModel.addElement(subscriber.getName());
		}
	}
	private void fillSubscriberTable(Subscriber subscriber) {
		subscribersTableModel.setColumnCount(0);
		subscribersTableModel.setRowCount(0);
		String[] colums= {"Name","Address","BillingType"};
		for (String columnName : colums) {
            subscribersTableModel.addColumn(columnName);
        }
		Vector<Subscriber> subscribers=new Vector();
		if(subscriber!=null) {
			subscribers.add(subscriber);
		}else {
			subscribers.addAll(distributor.getSubscribers());
		}
		for(Subscriber s:subscribers) {
			String billingType="Corpartion";
			if(s instanceof Individual) {
				billingType="Individual";
			}
			Object[] rowData = {s.getName(),s.getAddress(),billingType};
		    subscribersTableModel.addRow(rowData);
		}
	}
	private void fillJourneysTable(Journal journal) {
		journeysTableModel.setColumnCount(0);
		journeysTableModel.setRowCount(0);
		String[] colums= {"Name","Issn","Frequency","Issiue Price"};
		for (String columnName : colums) {
			journeysTableModel.addColumn(columnName);
        }
		ArrayList<Journal> journals=new ArrayList();
		if(journal!=null) {
			journals.add(journal);
		}else {
			journals.addAll(distributor.getJournals().values());
			System.out.println("journals size:"+distributor.getJournals().values().size());

		}
		
		if(!journals.isEmpty()) {
			for(Journal j:journals) {
				Object[] rowData = {j.getName(),j.getIssn(),j.getFrequency(),j.getIssuePrice()};
				journeysTableModel.addRow(rowData);
			}
		}
	        
	}
	private void fillMainTable(Vector<Subscription> subscriptionList) {
		mainTableModel.setColumnCount(0);
		mainTableModel.setRowCount(0);
		String[] colums= {"Issn","Subscriber","Start Month","Start Year", "Price"};
		for (String columnName : colums) {
			mainTableModel.addColumn(columnName);
        }
		if(subscriptionList ==null) {
			Hashtable<String,Journal> journals=distributor.getJournals();
			if(journals!=null && !journals.isEmpty()) {
				for(Map.Entry<String, Journal> entry:journals.entrySet()) {
					Journal journal=entry.getValue();
					Vector<Subscription> subscriptions= journal.getSubscriptions();
					if(subscriptions!=null && !subscriptions.isEmpty()) {
						for(Subscription subscription:subscriptions) {
							DateInfo dateInfo=subscription.getDates();
							Subscriber subscriber= subscription.getSubscriber();
							Object[] rowData = {entry.getKey(),subscriber.getName(),dateInfo.getStartMonth(),dateInfo.getStartYear(),""};
							mainTableModel.addRow(rowData);
						}
					}
				}
			}
		}else {
			for(Subscription subscription:subscriptionList) {
				Subscriber subscriber=subscription.getSubscriber();
				Journal journal = subscription.getJournal();
				DateInfo dateInfo=subscription.getDates();
				Object[] rowData = {journal.getIssn(),subscriber.getName(),dateInfo.getStartMonth(),dateInfo.getStartYear(),""};
				mainTableModel.addRow(rowData);
			}
		}
		
	}
	
	private void actionListeners() {
		mainMenuMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPagePanel.setVisible(true);
				journeyPanel.setVisible(false);
				subscribersPanel.setVisible(false);
				fillSubscriberList();
			}
		});
		
		journeyMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPagePanel.setVisible(false);
				journeyPanel.setVisible(true);
				subscribersPanel.setVisible(false);
			}
		});
		subscribersMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPagePanel.setVisible(false);
				journeyPanel.setVisible(false);
				subscribersPanel.setVisible(true);
			}
			
		});
		
		mainSubscriptionAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String issn= mainIssnTextField.getText();
				Journal journal= distributor.searchJournal(issn);
				String subscriberName= mainSubscriberList.getSelectedValue().toString();
				Subscriber subscriber=distributor.searchSubscriber(subscriberName);
				String startMonthStr= mainStartMonthTextField.getText();
				String startYearStr=mainStartYearTextField.getText();
				String priceStr =mainPaymentPriceTextField.getText();
				String discountRatioStr=mainDiscountTextField.getText();
				if(journal ==null) {
					System.out.println("Can not found journal!");
				}else if(Distributor.isNumeric(startMonthStr) && Distributor.isNumeric(startYearStr)){
					int startMonth=Integer.valueOf(startMonthStr);
					int startYear=Integer.valueOf(startYearStr);
					
					DateInfo dateInfo=new DateInfo(startMonth,(startMonth+11)%12,startYear);
					System.out.println(dateInfo.toString());
					try {
						PaymentInfo paymentInfo=new PaymentInfo();
						double journalPrice=journal.getIssuePrice();
						double price=Double.valueOf(priceStr);
						if(!discountRatioStr.isEmpty() && Distributor.isNumeric(discountRatioStr)){
							int discount =Integer.valueOf(discountRatioStr);
							if(journalPrice != 0 && discount != 0) {
								journalPrice -= (journalPrice*discount) / 100;
								paymentInfo.setDiscountRatio(discount);
							}
						}
						if(price <= journalPrice) {
							paymentInfo.setReceivedPayment(journalPrice-price);
							Subscription subscription=new Subscription(dateInfo,0,journal,subscriber);
							subscription.setPayment(paymentInfo);
							distributor.addSubscription(issn, subscription);
							fillMainTable(null);
						}else {
							System.out.println("Price more than the price of the Journal!");
						}
						
						
						
					}catch(Exception ex){
						System.out.println("Price must be double format!");
					}
					//Subscription subscription =new Subscription();	
				}else {
					System.out.println("Month and Year must be numeric!");
				}
				
			}
		});
		
		billingTypeComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected=billingTypeComboBox.getSelectedItem().toString();
				if(selected.equals("Individual")) {
					individualPanel.setVisible(true);
					corporatePanel.setVisible(false);
					
				}else if(selected.equals("Corporation")) {
					individualPanel.setVisible(false);
					corporatePanel.setVisible(true);
				}
			}
		});
		
		addSubscriversButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=subscriberNameTextField.getText();
				String address=addressTextPane.getText();
				String selectedBillingType=billingTypeComboBox.getSelectedItem().toString();
				if(selectedBillingType.equals("Individual")) {
					Individual individual=new Individual(name,address);
					individual.setCreaditCardNr(creditCardNumberTextField.getText());
					individual.setCCV(Integer.valueOf(creditCardCVCTextField.getText()));
					int month=Integer.valueOf(creditCardMonthComboBox.getSelectedItem().toString());
					int year=Integer.valueOf(creditCardYearComboBox.getSelectedItem().toString());
					individual.setExpireeMonth(month);
					individual.setExpireYear(year);
					distributor.addSubscriber(individual);
				
					
				}else if(selectedBillingType.equals("Corporation")) {
					Corporation corporation=new Corporation(name,address);
					String bankAccountNumber= bankAccountNumberTextField.getText();
					String bankCode=bankCodeTextField.getText();
					String bankDMY=bankDMYTextField.getText();
					String bankName=bankNameTextField.getText();
					corporation.setAccountNumber(Integer.valueOf(bankAccountNumber));
					corporation.setBankCode(Integer.valueOf(bankCode));
					corporation.setBankName(bankName);
					String[] dmy=bankDMY.split("/");
					corporation.setIssueDay(Integer.valueOf(dmy[0]));
					corporation.setIssueMonth(Integer.valueOf(dmy[1]));
					corporation.setIssueYear(Integer.valueOf(dmy[2]));
					distributor.addSubscriber(corporation);
				}
				fillSubscriberTable(null);
			}
		});
		
		searchSubscriberTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchKey=searchSubscriberTextField.getText();
				if(searchKey.equals("")) {
					fillSubscriberTable(null);
				}else {
					Subscriber subscriber=distributor.searchSubscriber(searchKey);
					if(subscriber !=null) {

						fillSubscriberTable(subscriber);
					}else {
						subscribersTableModel.setRowCount(0);
					}
				}
			}
		});
		
		
		journalAddButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name=journalNameTextField.getText();
				String issn= journalIssnTextField.getText();
				String price= journalPriceTextField.getText();
				int frequency=Integer.valueOf(journalFrequencyComboBox.getSelectedItem().toString());
				try {
					Double priceDouble=Double.valueOf(price);
					Journal journal=new Journal(name,issn,frequency,priceDouble);
					distributor.addJournal(journal);
				}catch(Exception ex) {
					System.out.println("Cast exception.");
				}
				
				fillJourneysTable(null);
			}
		});
		
		searchJourneyTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchKey=searchJourneyTextField.getText();
				if(searchKey.equals("")) {
					fillJourneysTable(null);
				}else {
					Journal journal=distributor.searchJournal(searchKey);
					if(journal !=null) {

						fillJourneysTable(journal);
					}else {
						journeysTableModel.setRowCount(0);
					}
				}
			}
		});
		
		

		loadStateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				distributor.loadState("stateFile");
				fillJourneysTable(null);
				fillSubscriberList();
				fillSubscriberTable(null);
				fillMainTable(null);
			}
		});
		listNameOrIssnButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String searchKey=mainSearchTextField.getText();
				if(searchKey.equals("")) {
					fillMainTable(null);
				}else {
					Vector<Subscription> subscriptionList= distributor.listSubscriptions(searchKey);
					fillMainTable(subscriptionList);
				}
			}
		});
		
		listSendingOrdersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String yearStr=mainFilterYearTextField.getText();
				String monthStr=mainFilterMonthTextField.getText();
				
				int year=Integer.valueOf(yearStr);
				int month=Integer.valueOf(monthStr);

				Vector<Subscription> subscriptionList=new Vector();
				String issn=mainFilterIssnTextField.getText();
				if(issn.equals("")) {
					for(Journal journal:distributor.getJournals().values()) {
						if(journal !=null &&  journal.getSubscriptions() !=null) {
							for(Subscription s: journal.getSubscriptions()) {
								if(s.canSend(month,year)) {
									subscriptionList.add(s);
								}
							}
							fillMainTable(subscriptionList);
						}
					}
					
				}else {
					Journal journal=distributor.searchJournal(issn);
					if(journal !=null) {
						for(Subscription s: journal.getSubscriptions()) {
							if(s.canSend(month,year)) {
								subscriptionList.add(s);
							}
						}
						fillMainTable(subscriptionList);
					}
				}
				
			}
		});
		
		inCompletePaymentsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Vector<Subscription> subscriptionList=new Vector();
				
				for(Journal journal:distributor.getJournals().values()) {
					if(journal !=null &&  journal.getSubscriptions() !=null) {
						for(Subscription s: journal.getSubscriptions()) {
							PaymentInfo payment=s.getPayment();
							if(payment!=null && payment.getReceivedPayment() != 0) {
								subscriptionList.add(s);
							}
						}
						fillMainTable(subscriptionList);
					}
				}
			}
		});
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 821, 507);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		menuBar.add(mainMenuMenuItem);
		menuBar.add(journeyMenuItem);
		menuBar.add(subscribersMenuItem);
		frame.getContentPane().setLayout(null);
		
		journeyPanel.setVisible(false);
		mainPagePanel.setVisible(false);
		
		mainPagePanel.setBounds(0, 0, 815, 451);
		frame.getContentPane().add(mainPagePanel);
		mainPagePanel.setLayout(null);
		
		JLabel lblNewLabel_13 = new JLabel("Subscriptions");
		lblNewLabel_13.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel_13.setBounds(377, 5, 190, 16);
		mainPagePanel.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("Add Subscriptions");
		lblNewLabel_14.setBounds(41, 42, 172, 16);
		mainPagePanel.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("Issn");
		lblNewLabel_15.setBounds(6, 76, 61, 16);
		mainPagePanel.add(lblNewLabel_15);
		
		mainIssnTextField = new JTextField();
		mainIssnTextField.setBounds(93, 70, 130, 26);
		mainPagePanel.add(mainIssnTextField);
		mainIssnTextField.setColumns(10);
		
		JLabel lblNewLabel_16 = new JLabel("Subscriber");
		lblNewLabel_16.setBounds(6, 116, 75, 16);
		mainPagePanel.add(lblNewLabel_16);
		
		mainSubscriberList.setBounds(93, 108, 130, 96);
		mainPagePanel.add(mainSubscriberList);
		
		JLabel lblNewLabel_17 = new JLabel("Start Month");
		lblNewLabel_17.setBounds(6, 218, 80, 16);
		mainPagePanel.add(lblNewLabel_17);
		
		JLabel lblNewLabel_18 = new JLabel("Start Year");
		lblNewLabel_18.setBounds(6, 246, 80, 16);
		mainPagePanel.add(lblNewLabel_18);
		
		mainStartMonthTextField = new JTextField();
		mainStartMonthTextField.setBounds(93, 216, 130, 26);
		mainPagePanel.add(mainStartMonthTextField);
		mainStartMonthTextField.setColumns(10);
		
		mainStartYearTextField = new JTextField();
		mainStartYearTextField.setBounds(93, 241, 130, 26);
		mainPagePanel.add(mainStartYearTextField);
		mainStartYearTextField.setColumns(10);
		
		mainPaymentPriceTextField = new JTextField();
		mainPaymentPriceTextField.setBounds(103, 279, 120, 26);
		mainPagePanel.add(mainPaymentPriceTextField);
		mainPaymentPriceTextField.setColumns(10);
		
		JLabel lblNewLabel_19 = new JLabel("Payment Price");
		lblNewLabel_19.setBounds(6, 284, 103, 16);
		mainPagePanel.add(lblNewLabel_19);
		
		
		mainSubscriptionAddButton.setBounds(93, 357, 130, 29);
		mainPagePanel.add(mainSubscriptionAddButton);
		
		mainTable = new JTable();
		mainTable.setBounds(339, 77, 443, 230);
		mainPagePanel.add(mainTable);
		
		subscribersPanel.setBounds(0, 0, 815, 451);
		frame.getContentPane().add(subscribersPanel);
		subscribersPanel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setBounds(22, 27, 61, 16);
		subscribersPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("Subscribers");
		lblNewLabel.setBounds(317, 5, 73, 16);
		subscribersPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1_1 = new JLabel("Address");
		lblNewLabel_1_1.setBounds(22, 67, 61, 16);
		subscribersPanel.add(lblNewLabel_1_1);
		
		subscriberNameTextField = new JTextField();
		subscriberNameTextField.setBounds(94, 22, 130, 26);
		subscribersPanel.add(subscriberNameTextField);
		subscriberNameTextField.setColumns(10);
		
		addressTextPane.setBounds(95, 67, 164, 59);
		subscribersPanel.add(addressTextPane);
		
		
		billingTypeComboBox.setModel(new DefaultComboBoxModel(new String[] {"Individual", "Corporation"}));
		billingTypeComboBox.setBounds(94, 149, 164, 35);
		subscribersPanel.add(billingTypeComboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Billing Type");
		lblNewLabel_2.setBounds(22, 157, 73, 16);
		subscribersPanel.add(lblNewLabel_2);
		
		
		addSubscriversButton.setBounds(22, 360, 256, 16);
		subscribersPanel.add(addSubscriversButton);
		
		individualPanel.setBounds(22, 193, 256, 115);
		subscribersPanel.add(individualPanel);
		individualPanel.setLayout(null);
		
		JLabel lblNewLabel_3 = new JLabel("Credit Card");
		lblNewLabel_3.setBounds(64, -1, 83, 16);
		individualPanel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Number");
		lblNewLabel_4.setBounds(6, 32, 61, 16);
		individualPanel.add(lblNewLabel_4);
		
		creditCardNumberTextField = new JTextField();
		creditCardNumberTextField.setBounds(64, 27, 141, 26);
		individualPanel.add(creditCardNumberTextField);
		creditCardNumberTextField.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("M/Y");
		lblNewLabel_5.setBounds(6, 60, 61, 16);
		individualPanel.add(lblNewLabel_5);
		
		creditCardMonthComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		creditCardMonthComboBox.setBounds(64, 60, 72, 27);
		individualPanel.add(creditCardMonthComboBox);
		
		creditCardYearComboBox.setModel(new DefaultComboBoxModel(new String[] {"2024", "2025", "2026", "2027", "2028", "2029", "2030", "2031", "2032", "2033", "2034", "2035", "2036", "2037", "2038"}));
		creditCardYearComboBox.setBounds(136, 60, 93, 27);
		individualPanel.add(creditCardYearComboBox);
		
		JLabel lblNewLabel_6 = new JLabel("CVC");
		lblNewLabel_6.setBounds(6, 88, 61, 16);
		individualPanel.add(lblNewLabel_6);
		
		creditCardCVCTextField = new JTextField();
		creditCardCVCTextField.setBounds(64, 88, 130, 26);
		individualPanel.add(creditCardCVCTextField);
		creditCardCVCTextField.setColumns(3);
		
		corporatePanel.setLayout(null);
		corporatePanel.setBounds(22, 197, 256, 134);
		subscribersPanel.add(corporatePanel);
		
		JLabel lblNewLabel_3_1 = new JLabel("Bank");
		lblNewLabel_3_1.setBounds(64, -1, 83, 16);
		corporatePanel.add(lblNewLabel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("Bank Code");
		lblNewLabel_4_1.setBounds(6, 32, 70, 16);
		corporatePanel.add(lblNewLabel_4_1);
		
		bankCodeTextField = new JTextField();
		bankCodeTextField.setColumns(10);
		bankCodeTextField.setBounds(88, 27, 141, 26);
		corporatePanel.add(bankCodeTextField);
		
		JLabel lblNewLabel_5_1 = new JLabel("Bank Name");
		lblNewLabel_5_1.setBounds(6, 60, 70, 16);
		corporatePanel.add(lblNewLabel_5_1);
		
		JLabel lblNewLabel_6_1 = new JLabel("D/M/Y");
		lblNewLabel_6_1.setBounds(6, 88, 70, 21);
		corporatePanel.add(lblNewLabel_6_1);
		
		bankDMYTextField = new JTextField();
		bankDMYTextField.setColumns(3);
		bankDMYTextField.setBounds(88, 85, 141, 26);
		corporatePanel.add(bankDMYTextField);
		
		bankNameTextField = new JTextField();
		bankNameTextField.setColumns(10);
		bankNameTextField.setBounds(88, 55, 141, 26);
		corporatePanel.add(bankNameTextField);
		
		JLabel lblNewLabel_5_1_1 = new JLabel("Account N.");
		lblNewLabel_5_1_1.setBounds(6, 112, 70, 16);
		corporatePanel.add(lblNewLabel_5_1_1);
		
		bankAccountNumberTextField = new JTextField();
		bankAccountNumberTextField.setColumns(10);
		bankAccountNumberTextField.setBounds(88, 107, 141, 26);
		corporatePanel.add(bankAccountNumberTextField);
		
		searchJourneyTextField = new JTextField();

		
		subscriberTable = new JTable();
		subscriberTable.setBounds(335, 95, 439, 281);
		subscribersPanel.add(subscriberTable);
		
		JLabel lblNewLabel_12 = new JLabel("Search");
		lblNewLabel_12.setBounds(340, 67, 61, 16);
		subscribersPanel.add(lblNewLabel_12);
		
		searchSubscriberTextField = new JTextField();
		
		searchSubscriberTextField.setBounds(413, 62, 361, 26);
		subscribersPanel.add(searchSubscriberTextField);
		searchSubscriberTextField.setColumns(10);
		journeyPanel.setBounds(0, 0, 815, 467);
		frame.getContentPane().add(journeyPanel);
		journeyPanel.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(30, 58, 61, 16);
		journeyPanel.add(lblName);
		
		JLabel lblNewLabel_7 = new JLabel("Journeys");
		lblNewLabel_7.setBounds(413, 5, 54, 16);
		journeyPanel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Issn");
		lblNewLabel_8.setBounds(30, 86, 61, 16);
		journeyPanel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("Frequency");
		lblNewLabel_9.setBounds(30, 113, 81, 16);
		journeyPanel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("Price");
		lblNewLabel_10.setBounds(30, 141, 61, 16);
		journeyPanel.add(lblNewLabel_10);
		
		journalNameTextField = new JTextField();
		journalNameTextField.setBounds(119, 53, 130, 26);
		journeyPanel.add(journalNameTextField);
		journalNameTextField.setColumns(10);
		
		journalIssnTextField = new JTextField();
		journalIssnTextField.setBounds(119, 81, 130, 26);
		journeyPanel.add(journalIssnTextField);
		journalIssnTextField.setColumns(10);
		
		journalFrequencyComboBox.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		journalFrequencyComboBox.setBounds(123, 109, 126, 27);
		journeyPanel.add(journalFrequencyComboBox);
		
		journalPriceTextField = new JTextField();
		journalPriceTextField.setBounds(119, 136, 130, 26);
		journeyPanel.add(journalPriceTextField);
		journalPriceTextField.setColumns(10);
		
		
		journalAddButton.setBounds(114, 174, 135, 29);
		journeyPanel.add(journalAddButton);
		
		journeysTable = new JTable();
		journeysTable.setBounds(297, 73, 499, 268);
		journeyPanel.add(journeysTable);
		
		
		searchJourneyTextField.setBounds(362, 39, 428, 26);
		journeyPanel.add(searchJourneyTextField);
		searchJourneyTextField.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Search");
		lblNewLabel_11.setBounds(309, 44, 61, 16);
		journeyPanel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_20 = new JLabel("Issn");
		lblNewLabel_20.setBounds(339, 342, 33, 16);
		mainPagePanel.add(lblNewLabel_20);
		
		mainFilterIssnTextField = new JTextField();
		mainFilterIssnTextField.setBounds(378, 337, 80, 26);
		mainPagePanel.add(mainFilterIssnTextField);
		mainFilterIssnTextField.setColumns(10);
		
		JLabel lblNewLabel_21 = new JLabel("Month");
		lblNewLabel_21.setBounds(470, 342, 61, 16);
		mainPagePanel.add(lblNewLabel_21);
		
		mainFilterMonthTextField = new JTextField();
		mainFilterMonthTextField.setBounds(515, 337, 92, 26);
		mainPagePanel.add(mainFilterMonthTextField);
		mainFilterMonthTextField.setColumns(10);
		
		JLabel lblNewLabel_22 = new JLabel("Year");
		lblNewLabel_22.setBounds(619, 342, 61, 16);
		mainPagePanel.add(lblNewLabel_22);
		
		mainFilterYearTextField = new JTextField();
		mainFilterYearTextField.setBounds(665, 337, 80, 26);
		mainPagePanel.add(mainFilterYearTextField);
		mainFilterYearTextField.setColumns(10);
		
	
		listSendingOrdersButton.setBounds(339, 370, 192, 29);
		mainPagePanel.add(listSendingOrdersButton);
		
		
		inCompletePaymentsButton.setBounds(547, 370, 198, 29);
		mainPagePanel.add(inCompletePaymentsButton);
		
		JButton saveStateButton = new JButton("Save State");
		saveStateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				distributor.saveState("stateFile");
			}
		});
		saveStateButton.setBounds(341, 411, 190, 29);
		mainPagePanel.add(saveStateButton);
		
		
		loadStateButton.setBounds(547, 411, 198, 29);
		mainPagePanel.add(loadStateButton);
		
		JLabel lblNewLabel_23 = new JLabel("List bySubscriber Name or Journey Issn");
		lblNewLabel_23.setBounds(339, 33, 287, 16);
		mainPagePanel.add(lblNewLabel_23);
		
		mainSearchTextField = new JTextField();
		mainSearchTextField.setBounds(339, 50, 353, 26);
		mainPagePanel.add(mainSearchTextField);
		mainSearchTextField.setColumns(10);
		
		
		listNameOrIssnButton.setBounds(692, 50, 85, 29);
		mainPagePanel.add(listNameOrIssnButton);
	}
}
