/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionExtraCutinDetailComponent } from 'app/entities/m-gacha-rendition-extra-cutin/m-gacha-rendition-extra-cutin-detail.component';
import { MGachaRenditionExtraCutin } from 'app/shared/model/m-gacha-rendition-extra-cutin.model';

describe('Component Tests', () => {
  describe('MGachaRenditionExtraCutin Management Detail Component', () => {
    let comp: MGachaRenditionExtraCutinDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionExtraCutinDetailComponent>;
    const route = ({ data: of({ mGachaRenditionExtraCutin: new MGachaRenditionExtraCutin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionExtraCutinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionExtraCutinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionExtraCutinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionExtraCutin).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
