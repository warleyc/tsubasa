/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MAnnounceTextDetailComponent } from 'app/entities/m-announce-text/m-announce-text-detail.component';
import { MAnnounceText } from 'app/shared/model/m-announce-text.model';

describe('Component Tests', () => {
  describe('MAnnounceText Management Detail Component', () => {
    let comp: MAnnounceTextDetailComponent;
    let fixture: ComponentFixture<MAnnounceTextDetailComponent>;
    const route = ({ data: of({ mAnnounceText: new MAnnounceText(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MAnnounceTextDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MAnnounceTextDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MAnnounceTextDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mAnnounceText).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
