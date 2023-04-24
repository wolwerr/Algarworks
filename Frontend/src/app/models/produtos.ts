export interface IProdutos
{
  id?: number;
  nome: string;
  status: boolean
}

export interface IObjectCursos {
  content: IProdutos[];
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
