import pandas as pd
import sys

# script for loading csv files using pandas

def main():
    path_to_csv = sys.argv[1]
    data = pd.read_csv(path_to_csv)

    # columns' names
    print(str(list(data.columns))
          .replace('\'', '')
          .replace('[', '')
          .replace(']', ''))

    # rows
    for val in data.values:
        print(str(list(val))
              .replace('\'', '')
              .replace('[', '')
              .replace(']', ''))


main()
