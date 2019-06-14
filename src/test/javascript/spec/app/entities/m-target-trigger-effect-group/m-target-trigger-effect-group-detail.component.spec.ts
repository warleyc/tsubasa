/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetTriggerEffectGroupDetailComponent } from 'app/entities/m-target-trigger-effect-group/m-target-trigger-effect-group-detail.component';
import { MTargetTriggerEffectGroup } from 'app/shared/model/m-target-trigger-effect-group.model';

describe('Component Tests', () => {
  describe('MTargetTriggerEffectGroup Management Detail Component', () => {
    let comp: MTargetTriggerEffectGroupDetailComponent;
    let fixture: ComponentFixture<MTargetTriggerEffectGroupDetailComponent>;
    const route = ({ data: of({ mTargetTriggerEffectGroup: new MTargetTriggerEffectGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetTriggerEffectGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTargetTriggerEffectGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetTriggerEffectGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTargetTriggerEffectGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
