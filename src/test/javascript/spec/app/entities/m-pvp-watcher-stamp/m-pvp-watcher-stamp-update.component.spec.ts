/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpWatcherStampUpdateComponent } from 'app/entities/m-pvp-watcher-stamp/m-pvp-watcher-stamp-update.component';
import { MPvpWatcherStampService } from 'app/entities/m-pvp-watcher-stamp/m-pvp-watcher-stamp.service';
import { MPvpWatcherStamp } from 'app/shared/model/m-pvp-watcher-stamp.model';

describe('Component Tests', () => {
  describe('MPvpWatcherStamp Management Update Component', () => {
    let comp: MPvpWatcherStampUpdateComponent;
    let fixture: ComponentFixture<MPvpWatcherStampUpdateComponent>;
    let service: MPvpWatcherStampService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpWatcherStampUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(MPvpWatcherStampUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(MPvpWatcherStampUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(MPvpWatcherStampService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new MPvpWatcherStamp(123);
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
        const entity = new MPvpWatcherStamp();
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
