import { ICursos } from "./cursos";
import { IProdutos } from "./produtos";

export interface IOfertas
{
  code: string;
  internalName: string;
  price: string
  active: boolean
  produtos: IProdutos
  salesStartingAt: string
  salesEndingAt: string
  deliverables: ICursos[]
  supportDurationInDays: string
}

export interface IObjectOfertas {
  content: IOfertas[];
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
