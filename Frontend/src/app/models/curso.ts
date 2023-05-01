export interface ICurso {
  id?: number;
  name: string;
}

export interface IObjetctCurso {
  content: ICurso[];
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

