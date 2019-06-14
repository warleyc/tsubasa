/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MLeagueRegulationUpdateComponent } from 'app/entities/m-league-regulation/m-league-regulation-update.component';
import { MLeagueRegulationService } from 'app/entities/m-league-regulation/m-league-regulation.service';
import { MLeagueRegulation } from 'app/shared/model/m-league-regulation.model';

describe('Component Tests', () => {
  describe('MLeagueRegulation Management Update Component', () => {
    let comp: MLeagueRegulationUpdateComponent;
    let fixture: ComponentFixture<MLeagueRegulationUpdateComponent>;
    let service: MLeagueRegulationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MLeagueRegulationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MLeagueRegulationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MLeagueRegulationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MLeagueRegulationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MLeagueRegulation(123);
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
        const entity = new MLeagueRegulation();
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
