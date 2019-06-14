/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueEffectUpdateComponent } from 'app/entities/m-league-effect/m-league-effect-update.component';
import { MLeagueEffectService } from 'app/entities/m-league-effect/m-league-effect.service';
import { MLeagueEffect } from 'app/shared/model/m-league-effect.model';

describe('Component Tests', () => {
  describe('MLeagueEffect Management Update Component', () => {
    let comp: MLeagueEffectUpdateComponent;
    let fixture: ComponentFixture<MLeagueEffectUpdateComponent>;
    let service: MLeagueEffectService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueEffectUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MLeagueEffectUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MLeagueEffectUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLeagueEffectService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLeagueEffect(123);
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
        const entity = new MLeagueEffect();
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
