/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTriggerEffectRangeDetailComponent } from 'app/entities/m-trigger-effect-range/m-trigger-effect-range-detail.component';
import { MTriggerEffectRange } from 'app/shared/model/m-trigger-effect-range.model';

describe('Component Tests', () => {
  describe('MTriggerEffectRange Management Detail Component', () => {
    let comp: MTriggerEffectRangeDetailComponent;
    let fixture: ComponentFixture<MTriggerEffectRangeDetailComponent>;
    const route = ({ data: of({ mTriggerEffectRange: new MTriggerEffectRange(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTriggerEffectRangeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTriggerEffectRangeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTriggerEffectRangeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTriggerEffectRange).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
