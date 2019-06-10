/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MExtensionSaleDetailComponent } from 'app/entities/m-extension-sale/m-extension-sale-detail.component';
import { MExtensionSale } from 'app/shared/model/m-extension-sale.model';

describe('Component Tests', () => {
  describe('MExtensionSale Management Detail Component', () => {
    let comp: MExtensionSaleDetailComponent;
    let fixture: ComponentFixture<MExtensionSaleDetailComponent>;
    const route = ({ data: of({ mExtensionSale: new MExtensionSale(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MExtensionSaleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MExtensionSaleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MExtensionSaleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mExtensionSale).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
