import javax.imageio.IIOException;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class Texteditor implements ActionListener {
    JFrame frame;
    JMenuBar menubar;
    JTextArea textarea;
    JMenu file,edit;
    JMenuItem newfile,openfile,savefile;
    JMenuItem cut, copy, paste, selectall, close;
    Texteditor(){
        frame = new JFrame();

        menubar = new JMenuBar();
        textarea = new JTextArea();

        file = new JMenu("File");
        edit = new JMenu("Edit");

        newfile = new JMenuItem("New Window");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save File");

        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);

        file.add(newfile);
        file.add(openfile);
        file.add(savefile);

        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectall = new JMenuItem("Select All");
        close = new JMenuItem("Close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);
        edit.add(close);

        menubar.add(file);
        menubar.add(edit);

        frame.setJMenuBar(menubar);

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        panel.add(textarea,BorderLayout.CENTER);

        JScrollPane scrollPane = new JScrollPane(textarea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        panel.add(scrollPane);

        frame.add(panel);
        frame.setBounds(400,100,600,600);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==cut){
            textarea.cut();
        }
        if(e.getSource()==copy){
            textarea.copy();
        }
        if(e.getSource()==paste){
            textarea.paste();
        }
        if(e.getSource()==selectall){
            textarea.selectAll();
        }
        if(e.getSource()==close){
            System.exit(0);
        }
        if(e.getSource()==openfile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String filePath = file.getPath();
                try{
                    FileReader fileReader = new FileReader(filePath);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String intermediate = "",output = "";
                    while((intermediate= bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    textarea.setText(output);
                } catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(e.getSource()==savefile){
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    FileWriter fileWriter = new FileWriter(file);
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    textarea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(e.getSource()==newfile){
            Texteditor newtexteditor = new Texteditor();
        }
    }
    public static void main(String[] args) {
        Texteditor texteditor = new Texteditor();
    }

}