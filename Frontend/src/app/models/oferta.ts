import {ICurso} from "./curso";
import {IProduto} from "./produto";

export interface IOferta {
  code: string
  internalName: string
  price: string
  active: boolean
  product: IProduto
  salesStartingAt: string
  salesEndingAt: string
  deliverables: ICurso[]
  supportDurationInDays: string
}
