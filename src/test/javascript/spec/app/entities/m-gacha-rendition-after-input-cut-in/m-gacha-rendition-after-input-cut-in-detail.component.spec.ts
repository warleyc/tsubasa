/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGachaRenditionAfterInputCutInDetailComponent } from 'app/entities/m-gacha-rendition-after-input-cut-in/m-gacha-rendition-after-input-cut-in-detail.component';
import { MGachaRenditionAfterInputCutIn } from 'app/shared/model/m-gacha-rendition-after-input-cut-in.model';

describe('Component Tests', () => {
  describe('MGachaRenditionAfterInputCutIn Management Detail Component', () => {
    let comp: MGachaRenditionAfterInputCutInDetailComponent;
    let fixture: ComponentFixture<MGachaRenditionAfterInputCutInDetailComponent>;
    const route = ({ data: of({ mGachaRenditionAfterInputCutIn: new MGachaRenditionAfterInputCutIn(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGachaRenditionAfterInputCutInDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGachaRenditionAfterInputCutInDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGachaRenditionAfterInputCutInDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGachaRenditionAfterInputCutIn).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
