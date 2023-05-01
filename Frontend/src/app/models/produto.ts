export interface IProduto {
  id?: number;
  name: string;
  status: string;
}

export interface IObjetctProduto {
  content: IProduto[];
  empty: boolean;
  first: boolean;
  last: boolean;
  number: number;
  numberOfElements: number;
  pageAble: any;
  size: number;
  sort: any;
  totalElements: number;
  totalPages: number;
}
