/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package components.userManager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author lnt09
 */
public class UserCRUD extends javax.swing.JPanel {

    /**
     * Creates new form UserCRUD
     */
    public UserCRUD() {
        initComponents();
        setOpaque(false);
        banButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy giá trị của JToggleButton khi nó được nhấp
                boolean isSelected = banButton.isSelected();
                setBanButton(isSelected);
            }
        });

        createNewButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPICreateUser().execute();
            }
        });
        updateButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPIUpdateUser().execute();
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CallAPIDeleteUser().execute();
            }
        });
        idText.setVisible(false);
        banButton.setVisible(false);
    }

    private class CallAPIDeleteUser extends SwingWorker<String, Object> {

        @Override
        protected String doInBackground() {
            HttpURLConnection con = null;
            try {
                String api = "http://13.215.176.178:8881/admin/all-user/";

                String id = idText.getText();

                api = api + id;

                HttpRequest req = HttpRequest.newBuilder()
                        .uri(URI.create(api))
                        .DELETE()
                        .build();

                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

                int resCode = res.statusCode();
                String body = res.body();
                System.out.println("Response code: " + resCode);
                System.out.println("Data: " + res.body());

                JSONParser par = new JSONParser();
                JSONObject res1 = (JSONObject) par.parse(body);

                if (resCode == 200) {
                    JOptionPane.showMessageDialog(null, String.valueOf(res1.get("message")), "Notify", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, String.valueOf(res1.get("error")),  "ERROR", JOptionPane.ERROR_MESSAGE);
                }

//                client.()
                return "Done";
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Failed";
        }

        @Override
        protected void process(List<Object> chunks) {

        }

        @Override
        protected void done() {
            try {
                String result = get();
//                if (result != null) {
//                    JOptionPane.showMessageDialog(null, "Load users successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class CallAPIUpdateUser extends SwingWorker<String, Object> {

        @Override
        protected String doInBackground() {
            HttpURLConnection con = null;
            try {
                String api = "http://13.215.176.178:8881/admin/all-user/update-user";

                JSONObject user = new JSONObject();
                
                user.put("id", idText.getText());
                user.put("username", usernameText.getText());
                String password = String.valueOf(passwordText.getPassword());
                user.put("password", password);
                user.put("sex", String.valueOf(sexText.getSelectedItem()));
                user.put("address", addressText.getText());
                user.put("fullname", fullnameText.getText());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date tmp = dateOfBirthText.getDate();

                user.put("dateOfBirth", String.valueOf(dateFormat.format(tmp)));
                user.put("email", emailText.getText());
                user.put("status", String.valueOf(statusText.getSelectedItem()));

                String json = user.toString();
                System.out.println("User: " + json);

                HttpRequest req = HttpRequest.newBuilder()
                        .uri(URI.create(api))
                        .header("Content-Type", "application/json")
                        .method("PATCH", HttpRequest.BodyPublishers.ofString(json))
                        .build();

                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());

                int resCode = res.statusCode();
                String body = res.body();
                System.out.println("Response code: " + resCode);
                System.out.println("Data: " + res.body());

                JSONParser par = new JSONParser();
                JSONObject res1 = (JSONObject) par.parse(body);

                if (resCode == 200) {
                    System.out.println("Data" + res);
                    JOptionPane.showMessageDialog(null, String.valueOf(res1.get("message")), "Notify", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, String.valueOf(res1.get("error")), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

//                client.()
                return "Done";
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ParseException ex) {
                Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            }
            return "Failed";
        }

        @Override
        protected void process(List<Object> chunks) {

        }

        @Override
        protected void done() {
            try {
                String result = get();
//                if (result != null) {
//                    JOptionPane.showMessageDialog(null, "Load users successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private class CallAPICreateUser extends SwingWorker<String, Object> {

        @Override
        protected String doInBackground() {
            HttpURLConnection con = null;
            try {
                String api = "http://13.215.176.178:8881/admin/all-user/create-user";

                URL url = new URL(api);

                JSONObject user = new JSONObject();
                user.put("username", usernameText.getText());

                String password = String.valueOf(passwordText.getPassword());
                user.put("password", password);
                user.put("sex", String.valueOf(sexText.getSelectedItem()));
                user.put("address", addressText.getText());
                user.put("fullname", fullnameText.getText());

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date tmp = dateOfBirthText.getDate();

                user.put("dateOfBirth", String.valueOf(dateFormat.format(tmp)));
                user.put("email", emailText.getText());
                user.put("status", String.valueOf(statusText.getSelectedItem()));

                String json = user.toString();
                System.out.println("User: " + json);

//                try (DataOutputStream wr = new DataOutputStream(con.getOutputStream())) {
//                    wr.writeBytes(json);
//                    wr.flush();
//                }
//
//                int resCode = con.getResponseCode();
//                String resMsg = con.getResponseMessage();
//                System.out.println("Response code: " + resCode);
                HttpRequest req = HttpRequest.newBuilder()
                        .uri(URI.create(api))
                        .header("Content-Type", "application/json")
                        .method("POST", HttpRequest.BodyPublishers.ofString(json))
                        .build();
                HttpClient client = HttpClient.newHttpClient();
                HttpResponse<String> res = client.send(req, HttpResponse.BodyHandlers.ofString());
                int resCode = res.statusCode();
                String resBuf = res.body();
                System.out.println("Code" + res.statusCode());
                System.out.println("Data" + res.body());

                System.out.println("Data" + res);

                JSONParser par = new JSONParser();
                JSONObject res1 = (JSONObject) par.parse(resBuf);
                if (resCode == 200) {

                    JOptionPane.showMessageDialog(null, res1.get("message").toString(), "Notify", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, res1.get("error").toString(), "ERROR", JOptionPane.ERROR_MESSAGE);
                }

                return "Done";
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException ex) {
                Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ParseException ex) {
                Logger.getLogger(UserCRUD.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

            }

            return "Failed";
        }

        @Override
        protected void process(List<Object> chunks) {

        }

        @Override
        protected void done() {
            try {
                String result = get();
//                if (result != null) {
//                    JOptionPane.showMessageDialog(null, "Load users successfully", "Successfully", JOptionPane.INFORMATION_MESSAGE);
//                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")

    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
        super.paintComponent(grphcs);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        fullnameText = new javax.swing.JTextField();
        addressText = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        usernameText = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        emailText = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        createNewButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        updateButton = new javax.swing.JButton();
        passwordText = new javax.swing.JPasswordField();
        banButton = new javax.swing.JToggleButton();
        listFriendButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        loginHistoryButton = new javax.swing.JButton();
        idText = new javax.swing.JTextField();
        statusText = new javax.swing.JComboBox<>();
        sexText = new javax.swing.JComboBox<>();
        dateOfBirthText = new com.toedter.calendar.JDateChooser();

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(127, 127, 127));
        jLabel1.setText("User Name");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(127, 127, 127));
        jLabel2.setText("Password");

        jLabel3.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(127, 127, 127));
        jLabel3.setText("Address");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(127, 127, 127));
        jLabel4.setText("Sex");

        fullnameText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        fullnameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullnameTextActionPerformed(evt);
            }
        });

        addressText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addressText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addressTextMouseClicked(evt);
            }
        });
        addressText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addressTextActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(127, 127, 127));
        jLabel6.setText("Full Name");

        usernameText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        usernameText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(127, 127, 127));
        jLabel7.setText("Date of Birth ");

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(127, 127, 127));
        jLabel8.setText("Email");

        emailText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        emailText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(127, 127, 127));
        jLabel9.setText("Status");

        createNewButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        createNewButton.setForeground(new java.awt.Color(127, 127, 127));
        createNewButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/8.png"))); // NOI18N
        createNewButton.setText("Create New");
        createNewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createNewButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(127, 127, 127));
        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/6.png"))); // NOI18N
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        updateButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        updateButton.setForeground(new java.awt.Color(127, 127, 127));
        updateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/1.png"))); // NOI18N
        updateButton.setText("Update");
        updateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });

        passwordText.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordTextActionPerformed(evt);
            }
        });

        banButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        banButton.setForeground(new java.awt.Color(127, 127, 127));
        banButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/7.png"))); // NOI18N
        banButton.setText("Unban");
        banButton.setPreferredSize(new java.awt.Dimension(95, 24));
        banButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                banButtonActionPerformed(evt);
            }
        });

        listFriendButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        listFriendButton.setForeground(new java.awt.Color(127, 127, 127));
        listFriendButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/2.png"))); // NOI18N
        listFriendButton.setText("List Friend");
        listFriendButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listFriendButtonActionPerformed(evt);
            }
        });

        clearButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        clearButton.setForeground(new java.awt.Color(127, 127, 127));
        clearButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/3.png"))); // NOI18N
        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        loginHistoryButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        loginHistoryButton.setForeground(new java.awt.Color(127, 127, 127));
        loginHistoryButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/raven/icon/3.png"))); // NOI18N
        loginHistoryButton.setText("Login History");
        loginHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginHistoryButtonActionPerformed(evt);
            }
        });

        idText.setText("jTextField1");

        statusText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        statusText.setForeground(new java.awt.Color(127, 127, 127));
        statusText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "active", "inactive", "banned" }));

        sexText.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        sexText.setForeground(new java.awt.Color(127, 127, 127));
        sexText.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "male", "female", "other", "null" }));

        dateOfBirthText.setDate(new java.util.Date(1703421599000L));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(createNewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(banButton, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(addressText)
                                    .addComponent(usernameText, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                    .addComponent(passwordText)
                                    .addComponent(sexText, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(55, 55, 55)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(26, 26, 26)
                                        .addComponent(fullnameText, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(listFriendButton, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel7)
                                                    .addComponent(jLabel8))
                                                .addGap(6, 6, 6)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(emailText, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
                                                    .addComponent(dateOfBirthText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                                                .addComponent(statusText, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(clearButton)
                                            .addComponent(loginHistoryButton))))))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(fullnameText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(listFriendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(usernameText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordText, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(loginHistoryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateOfBirthText, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(emailText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(addressText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(statusText, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(sexText)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(idText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(updateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(createNewButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(banButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void fullnameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullnameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_fullnameTextActionPerformed

    private void addressTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addressTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTextActionPerformed

    private void usernameTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextActionPerformed

    private void emailTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextActionPerformed

    private void addressTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addressTextMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_addressTextMouseClicked

    private void createNewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createNewButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createNewButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void updateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_updateButtonActionPerformed

    private void passwordTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passwordTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passwordTextActionPerformed

    private void listFriendButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listFriendButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listFriendButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        usernameText.setText("");
        passwordText.setText("");
//        sexText.setText("");
        usernameText.setText("");
        addressText.setText("");
        fullnameText.setText("");
//        dateOfBirthText.setText("");
        emailText.setText("");


    }//GEN-LAST:event_clearButtonActionPerformed

    private void loginHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginHistoryButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_loginHistoryButtonActionPerformed

    private void banButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_banButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_banButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField addressText;
    private javax.swing.JToggleButton banButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JButton createNewButton;
    private com.toedter.calendar.JDateChooser dateOfBirthText;
    private javax.swing.JButton deleteButton;
    private javax.swing.JTextField emailText;
    private javax.swing.JTextField fullnameText;
    private javax.swing.JTextField idText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JButton listFriendButton;
    private javax.swing.JButton loginHistoryButton;
    private javax.swing.JPasswordField passwordText;
    private javax.swing.JComboBox<String> sexText;
    private javax.swing.JComboBox<String> statusText;
    private javax.swing.JButton updateButton;
    private javax.swing.JTextField usernameText;
    // End of variables declaration//GEN-END:variables

    // ------------------------------------
    public JButton getCreateNewButton() {
        return createNewButton;
    }

    public void setCreateNewButton(JButton createNewButton) {
        this.createNewButton = createNewButton;
    }

    public void addListenerCreateButton(ActionListener listener) {
        createNewButton.addActionListener(listener);
    }

    // -------------------------------------
    public JButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JButton updateButton1) {
        this.updateButton = updateButton1;
    }

    public void addListenerUpdateButton(ActionListener listener) {
        updateButton.addActionListener(listener);
    }
    // --------------------------------------

    public JButton getDeleteButton() {
        return deleteButton;
    }

    public void setDeleteButton(JButton deleteButton1) {
        this.deleteButton = deleteButton1;
    }

    public void addListenerDeleteButton(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    //-------------------------------------------
    public String getUsernameText() {
        return usernameText.getText();
    }

    public void addListenerListFriendButton(ActionListener listener) {
        listFriendButton.addActionListener(listener);
    }

    public void addListenerLoginHistoryButton(ActionListener listener) {
        loginHistoryButton.addActionListener(listener);
    }

    // --------------------------------------
    public void setInfo(String id, String username, String fullname, String password, String dateOfBirth, String sex, String email, String address, String status, Boolean ban) {
        idText.setText(id);
        usernameText.setText(username);
        fullnameText.setText(fullname);
        passwordText.setText("");

        if (dateOfBirth.length() != 4) {
            System.out.println("Date: " + dateOfBirthText.getDate());

            int year = Integer.parseInt(dateOfBirth.substring(0, 4));
            int month = Integer.parseInt(dateOfBirth.substring(5, 7));
            int day = Integer.parseInt(dateOfBirth.substring(8, 10));
//            System.out.println("DATE: " + dateOfBirth);
//            System.out.println("Year " + year);
//            System.out.println("Month " + month);
//            System.out.println("Day " + day);
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month - 1); // Giảm đi 1 vì tháng bắt đầu từ 0
            calendar.set(Calendar.DAY_OF_MONTH, day);
            Date tmp = calendar.getTime();
            dateOfBirthText.setDate(tmp);
        }

        if ("male".equals(sex)) {
            sexText.setSelectedIndex(0);
        }
        if ("female".equals(sex)) {
            sexText.setSelectedIndex(1);
        }
        if ("other".equals(sex)) {
            sexText.setSelectedIndex(2);
        }
        if ("null".equals(sex)) {
            sexText.setSelectedIndex(3);
        }
        addressText.setText(address);
        emailText.setText(email);

        if ("active".equals(status)) {
            statusText.setSelectedIndex(0);
        }
        if ("inactive".equals(status)) {
            statusText.setSelectedIndex(1);
        }
        if ("ban".equals(status)) {
            statusText.setSelectedIndex(2);
        }

        setBanButton(ban);
    }

    public String getId() {
        return idText.getText();
    }

    public void setBanButton(Boolean value) {
        banButton.setSelected(value);
        if (value) {
            banButton.setText("Ban");
        } else {
            banButton.setText("Unban");
        }
    }

}
