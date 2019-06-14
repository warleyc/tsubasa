/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTeamUpdateComponent } from 'app/entities/m-team/m-team-update.component';
import { MTeamService } from 'app/entities/m-team/m-team.service';
import { MTeam } from 'app/shared/model/m-team.model';

describe('Component Tests', () => {
  describe('MTeam Management Update Component', () => {
    let comp: MTeamUpdateComponent;
    let fixture: ComponentFixture<MTeamUpdateComponent>;
    let service: MTeamService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTeamUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MTeamUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MTeamUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MTeamService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MTeam(123);
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
        const entity = new MTeam();
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
