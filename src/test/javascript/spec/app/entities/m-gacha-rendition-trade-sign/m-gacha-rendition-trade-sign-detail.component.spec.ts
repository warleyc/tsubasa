/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionTradeSignDetailComponent } from 'app/entities/m-gacha-rendition-trade-sign/m-gacha-rendition-trade-sign-detail.component';
import { MGachaRenditionTradeSign } from 'app/shared/model/m-gacha-rendition-trade-sign.model';

describe('Component Tests', () => {
  describe('MGachaRenditionTradeSign Management Detail Component', () => {
    let comp: MGachaRenditionTradeSignDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionTradeSignDetailComponent>;
    const route = ({ data: of({ mGachaRenditionTradeSign: new MGachaRenditionTradeSign(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionTradeSignDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionTradeSignDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionTradeSignDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionTradeSign).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
