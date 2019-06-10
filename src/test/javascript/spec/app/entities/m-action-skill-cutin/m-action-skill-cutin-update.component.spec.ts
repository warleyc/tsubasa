/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionSkillCutinUpdateComponent } from 'app/entities/m-action-skill-cutin/m-action-skill-cutin-update.component';
import { MActionSkillCutinService } from 'app/entities/m-action-skill-cutin/m-action-skill-cutin.service';
import { MActionSkillCutin } from 'app/shared/model/m-action-skill-cutin.model';

describe('Component Tests', () => {
  describe('MActionSkillCutin Management Update Component', () => {
    let comp: MActionSkillCutinUpdateComponent;
    let fixture: ComponentFixture<MActionSkillCutinUpdateComponent>;
    let service: MActionSkillCutinService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionSkillCutinUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MActionSkillCutinUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MActionSkillCutinUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MActionSkillCutinService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MActionSkillCutin(123);
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
        const entity = new MActionSkillCutin();
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
