export interface ICursos
{
  id?: number;
  nome: string;
}

export interface IObjectCursos {
  content: ICursos[];
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
