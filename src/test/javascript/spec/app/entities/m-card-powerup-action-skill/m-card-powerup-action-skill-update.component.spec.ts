/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardPowerupActionSkillUpdateComponent } from 'app/entities/m-card-powerup-action-skill/m-card-powerup-action-skill-update.component';
import { MCardPowerupActionSkillService } from 'app/entities/m-card-powerup-action-skill/m-card-powerup-action-skill.service';
import { MCardPowerupActionSkill } from 'app/shared/model/m-card-powerup-action-skill.model';

describe('Component Tests', () => {
  describe('MCardPowerupActionSkill Management Update Component', () => {
    let comp: MCardPowerupActionSkillUpdateComponent;
    let fixture: ComponentFixture<MCardPowerupActionSkillUpdateComponent>;
    let service: MCardPowerupActionSkillService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardPowerupActionSkillUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MCardPowerupActionSkillUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MCardPowerupActionSkillUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MCardPowerupActionSkillService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MCardPowerupActionSkill(123);
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
        const entity = new MCardPowerupActionSkill();
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
