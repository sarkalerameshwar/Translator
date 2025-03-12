import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MultiLangTranslator {
    private static final HttpClient client = HttpClient.newHttpClient();
    
    public static String translate(String text, String targetLang) {
        try {
            // Convert language code for API
            String langCode = switch (targetLang.toLowerCase()) {
                case "french" -> "fr";
                case "german" -> "de";
                case "spanish" -> "es";
                case "italian" -> "it";
                case "portuguese" -> "pt";
                default -> "en";
            };

            // Encode the text and language pair for URL
            String encodedText = URLEncoder.encode(text, StandardCharsets.UTF_8);
            String encodedLangPair = URLEncoder.encode("en|" + langCode, StandardCharsets.UTF_8);
            
            // Create API URL
            String apiUrl = String.format(
                "https://api.mymemory.translated.net/get?q=%s&langpair=%s",
                encodedText, encodedLangPair
            );

            // Create HTTP request
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .GET()
                .build();

            // Send request and get response
            HttpResponse<String> response = client.send(request, 
                HttpResponse.BodyHandlers.ofString());

            // Parse response manually
            String responseBody = response.body();
            Pattern pattern = Pattern.compile("\"translatedText\":\"([^\"]+)\"");
            Matcher matcher = pattern.matcher(responseBody);
            
            if (matcher.find()) {
                return matcher.group(1);
            }
            
            return "Translation not found";
        } catch (Exception e) {
            e.printStackTrace();
            return "Translation error occurred";
        }
    }

    private static void setUIStyle() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        setUIStyle();
        
        // Create the main frame
        JFrame frame = new JFrame("Multi-Language Translator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(20, 20));
        frame.getContentPane().setBackground(new Color(240, 240, 245));

        // Create main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBackground(new Color(240, 240, 245));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Create title label
        JLabel titleLabel = new JLabel("Multi-Language Translator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 50));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        // Create styled input field
        JTextField inputField = new JTextField(20);
        inputField.setFont(new Font("Arial", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(100, 100, 100), 1, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(inputField, gbc);

        // Create styled language dropdown
        String[] languages = {"French", "German", "Spanish", "Italian", "Portuguese"};
        JComboBox<String> languageDropdown = new JComboBox<>(languages);
        languageDropdown.setFont(new Font("Arial", Font.PLAIN, 14));
        languageDropdown.setBackground(Color.WHITE);
        languageDropdown.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(100, 100, 100), 1, true),
            BorderFactory.createEmptyBorder(8, 10, 8, 10)
        ));
        gbc.gridy = 2;
        mainPanel.add(languageDropdown, gbc);

        // Create styled translate button
        JButton translateButton = new JButton("Translate");
        translateButton.setFont(new Font("Arial", Font.BOLD, 14));
        translateButton.setForeground(Color.WHITE);
        translateButton.setBackground(new Color(70, 130, 180));
        translateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        translateButton.setFocusPainted(false);
        translateButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Add hover effect to button
        translateButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                translateButton.setBackground(new Color(60, 110, 160));
            }
            public void mouseExited(MouseEvent e) {
                translateButton.setBackground(new Color(70, 130, 180));
            }
        });

        gbc.gridy = 3;
        mainPanel.add(translateButton, gbc);

        // Create result panel with card-like styling
        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BorderLayout());
        resultPanel.setBackground(Color.WHITE);
        resultPanel.setBorder(BorderFactory.createCompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1, true),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));

        // Create styled result label
        JLabel resultLabel = new JLabel("Translation will appear here", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        resultLabel.setForeground(new Color(70, 70, 70));
        resultPanel.add(resultLabel, BorderLayout.CENTER);

        gbc.gridy = 4;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        mainPanel.add(resultPanel, gbc);

        // Add action listener to translate button
        translateButton.addActionListener(e -> {
            String text = inputField.getText().trim();
            String language = (String) languageDropdown.getSelectedItem();
            
            if (!text.isEmpty() && !text.equals("Enter text to translate")) {
                translateButton.setEnabled(false);
                resultLabel.setText("Translating...");
                resultPanel.setBackground(Color.WHITE);
                
                // Run translation in background
                new SwingWorker<String, Void>() {
                    @Override
                    protected String doInBackground() {
                        return translate(text, language);
                    }

                    @Override
                    protected void done() {
                        try {
                            String translation = get();
                            resultLabel.setText(String.format("<html><div style='text-align: center;'>%s → <b>%s</b></div></html>", 
                                text, translation));
                            resultPanel.setBackground(new Color(240, 255, 240));
                        } catch (Exception ex) {
                            resultLabel.setText("Translation error occurred");
                            resultPanel.setBackground(new Color(255, 240, 240));
                        }
                        translateButton.setEnabled(true);
                    }
                }.execute();
            } else {
                resultLabel.setText("Please enter text to translate");
                resultPanel.setBackground(new Color(255, 240, 240));
            }
        });

        // Add key listener to input field for Enter key
        inputField.addActionListener(e -> translateButton.doClick());

        // Add placeholder text to input field
        inputField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (inputField.getText().equals("Enter text to translate")) {
                    inputField.setText("");
                    inputField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (inputField.getText().isEmpty()) {
                    inputField.setForeground(Color.GRAY);
                    inputField.setText("Enter text to translate");
                }
            }
        });
        inputField.setForeground(Color.GRAY);
        inputField.setText("Enter text to translate");

        // Add main panel to frame
        frame.add(mainPanel, BorderLayout.CENTER);

        // Display the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
