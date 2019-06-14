/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetTriggerEffectGroupUpdateComponent } from 'app/entities/m-target-trigger-effect-group/m-target-trigger-effect-group-update.component';
import { MTargetTriggerEffectGroupService } from 'app/entities/m-target-trigger-effect-group/m-target-trigger-effect-group.service';
import { MTargetTriggerEffectGroup } from 'app/shared/model/m-target-trigger-effect-group.model';

describe('Component Tests', () => {
  describe('MTargetTriggerEffectGroup Management Update Component', () => {
    let comp: MTargetTriggerEffectGroupUpdateComponent;
    let fixture: ComponentFixture<MTargetTriggerEffectGroupUpdateComponent>;
    let service: MTargetTriggerEffectGroupService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetTriggerEffectGroupUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTargetTriggerEffectGroupUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTargetTriggerEffectGroupUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTargetTriggerEffectGroupService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTargetTriggerEffectGroup(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTargetTriggerEffectGroup();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
