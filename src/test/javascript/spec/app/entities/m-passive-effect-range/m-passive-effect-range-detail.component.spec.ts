/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPassiveEffectRangeDetailComponent } from 'app/entities/m-passive-effect-range/m-passive-effect-range-detail.component';
import { MPassiveEffectRange } from 'app/shared/model/m-passive-effect-range.model';

describe('Component Tests', () => {
  describe('MPassiveEffectRange Management Detail Component', () => {
    let comp: MPassiveEffectRangeDetailComponent;
    let fixture: ComponentFixture<MPassiveEffectRangeDetailComponent>;
    const route = ({ data: of({ mPassiveEffectRange: new MPassiveEffectRange(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPassiveEffectRangeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPassiveEffectRangeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPassiveEffectRangeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPassiveEffectRange).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
