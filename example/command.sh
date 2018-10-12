# Convert
java -jar morphviz-0.1.0.jar -i morph.xml -o morph.dot

# Create PDF
dot -Tpdf morph.dot -o morph.pdf

# Or use
# https://dreampuf.github.io/GraphvizOnline/