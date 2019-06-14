/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTriggerEffectBaseDetailComponent } from 'app/entities/m-trigger-effect-base/m-trigger-effect-base-detail.component';
import { MTriggerEffectBase } from 'app/shared/model/m-trigger-effect-base.model';

describe('Component Tests', () => {
  describe('MTriggerEffectBase Management Detail Component', () => {
    let comp: MTriggerEffectBaseDetailComponent;
    let fixture: ComponentFixture<MTriggerEffectBaseDetailComponent>;
    const route = ({ data: of({ mTriggerEffectBase: new MTriggerEffectBase(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTriggerEffectBaseDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTriggerEffectBaseDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTriggerEffectBaseDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTriggerEffectBase).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
