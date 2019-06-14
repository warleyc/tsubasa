/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTeamEffectBaseUpdateComponent } from 'app/entities/m-team-effect-base/m-team-effect-base-update.component';
import { MTeamEffectBaseService } from 'app/entities/m-team-effect-base/m-team-effect-base.service';
import { MTeamEffectBase } from 'app/shared/model/m-team-effect-base.model';

describe('Component Tests', () => {
  describe('MTeamEffectBase Management Update Component', () => {
    let comp: MTeamEffectBaseUpdateComponent;
    let fixture: ComponentFixture<MTeamEffectBaseUpdateComponent>;
    let service: MTeamEffectBaseService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTeamEffectBaseUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTeamEffectBaseUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTeamEffectBaseUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTeamEffectBaseService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTeamEffectBase(123);
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
        const entity = new MTeamEffectBase();
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
