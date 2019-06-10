/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionAfterInputCutInTextColorDetailComponent } from 'app/entities/m-gacha-rendition-after-input-cut-in-text-color/m-gacha-rendition-after-input-cut-in-text-color-detail.component';
import { MGachaRenditionAfterInputCutInTextColor } from 'app/shared/model/m-gacha-rendition-after-input-cut-in-text-color.model';

describe('Component Tests', () => {
  describe('MGachaRenditionAfterInputCutInTextColor Management Detail Component', () => {
    let comp: MGachaRenditionAfterInputCutInTextColorDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionAfterInputCutInTextColorDetailComponent>;
    const route = ({
      data: of({ mGachaRenditionAfterInputCutInTextColor: new MGachaRenditionAfterInputCutInTextColor(123) })
    } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionAfterInputCutInTextColorDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionAfterInputCutInTextColorDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionAfterInputCutInTextColorDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionAfterInputCutInTextColor).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
